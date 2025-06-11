public class ReseñaManga extends Reseña {
    private Manga manga;

    public ReseñaManga(Manga manga, int puntuacionHistoria, int puntuacionDibujo, String comentario) {
        super(puntuacionHistoria, puntuacionDibujo, comentario);
        this.manga = manga;
    }

    public Manga getManga() {
        return manga;
    }

    public String getInfoCompleta() {
        return "MANGA: " + manga.getInfoBasica() + "\n" +
                "PUNTUACIÓN HISTORIA: " + getEstrellasHistoria() + "\n" +
                "PUNTUACIÓN DIBUJO: " + getEstrellasDibujo() + "\n" +
                "COMENTARIO: " + getComentario();
    }
}