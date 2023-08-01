import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HTMLTagContador {

    public static void main(String[] args) {
        // Informe as URLs que deseja analisar
        List<String> urls = new ArrayList<>();
        urls.add("https://www.lncc.br/~borges/php/testar.html");
        urls.add("https://www.example.com");

        try {
            // Configuração do banco de dados (substitua pelos seus dados de conexão)
            String urlBanco = "jdbc:postgresql://localhost:5432/contador-html";
            String usuario = "postgres";
            String senha = "lucarauj";

            // Conexão com o banco de dados
            Connection conn = DriverManager.getConnection(urlBanco, usuario, senha);

            // Processa cada URL individualmente
            for (String url : urls) {
                processarURL(conn, url);
            }

            // Fecha a conexão com o banco de dados
            conn.close();

            System.out.println("Processamento concluído.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void processarURL(Connection conn, String url) throws SQLException {
        try {
            // Faz o download do código HTML
            String html = downloadHtml(url);

            // Analisa o código HTML e conta as tags
            contarHTMLTags(conn, url, html);
        } catch (IOException e) {
            System.err.println("Erro ao processar a URL: " + url);
            e.printStackTrace();
        }
    }

    private static String downloadHtml(String url) throws IOException {
        return Jsoup.connect(url).get().html();
    }

    private static void contarHTMLTags(Connection conn, String url, String html) throws SQLException {
        Document doc = Jsoup.parse(html);
        Elements tags = doc.select("*");

        // Prepara a declaração SQL para inserir as informações no banco de dados
        String sql = "INSERT INTO html_tags (url, tag, contar) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        // Conta as tags e as insere no banco de dados
        for (Element tag : tags) {
            String tagName = tag.tagName();

            if (tagName.equals("#root")) {
                continue;
            }

            // Verifica se a tag já existe no banco de dados
            String selectQuery = "SELECT id, contar FROM html_tags WHERE url = ? AND tag = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
            selectStmt.setString(1, url);
            selectStmt.setString(2, tagName);
            ResultSet resultSet = selectStmt.executeQuery();

            // Se a tag já existe, atualiza a contagem
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int contar = resultSet.getInt("contar");
                String updateQuery = "UPDATE html_tags SET contar = ? WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, contar + 1);
                updateStmt.setInt(2, id);
                updateStmt.executeUpdate();
                updateStmt.close();
            } else {
                // Se a tag não existe, insere uma nova entrada
                String insertQuery = "INSERT INTO html_tags (url, tag, contar) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, url);
                insertStmt.setString(2, tagName);
                insertStmt.setInt(3, 1);
                insertStmt.executeUpdate();
                insertStmt.close();
            }

            selectStmt.close();
            resultSet.close();
        }
    }

}
