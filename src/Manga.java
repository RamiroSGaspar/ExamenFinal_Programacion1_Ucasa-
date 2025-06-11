public class Manga {
    private String titulo;
    private String autor;
    private String genero;

    public Manga(String titulo, String autor, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getInfoBasica() {
        return titulo + " (" + autor + ") - " + genero;
    }
}