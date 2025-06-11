public class Reseña {

    private int puntuacionHistoria;
    private int puntuacionDibujo;
    private String comentario;

    public Reseña(int puntuacionHistoria, int puntuacionDibujo, String comentario) {
        this.puntuacionHistoria = puntuacionHistoria;
        this.puntuacionDibujo = puntuacionDibujo;
        this.comentario = comentario;
    }

    public int getPuntuacionHistoria() {
        return puntuacionHistoria;
    }

    public int getPuntuacionDibujo() {
        return puntuacionDibujo;
    }

    public String getComentario() {
        return comentario;
    }

    public String getEstrellasHistoria() {
        return "★".repeat(puntuacionHistoria) + "☆".repeat(5 - puntuacionHistoria);
    }

    public String getEstrellasDibujo() {
        return "★".repeat(puntuacionDibujo) + "☆".repeat(5 - puntuacionDibujo);
    }
}
