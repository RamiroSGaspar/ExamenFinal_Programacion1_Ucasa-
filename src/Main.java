import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static final int MAX_MANGAS = 10;
    private static final int MAX_RESEÑAS = 50;
    private static Manga[] mangas = new Manga[MAX_MANGAS];
    private static ReseñaManga[] reseñas = new ReseñaManga[MAX_RESEÑAS];
    private static int contadorMangas = 0;
    private static int contadorReseñas = 0;
    private static Scanner scanner = new Scanner(System.in);

    // Géneros predefinidos con descripciones
    private static final Genero[] GENEROS = {
            new Genero("Shonen", "Dirigido a chicos adolescentes. Acción, aventura y amistad. Ej: One Piece, Naruto"),
            new Genero("Shojo", "Dirigido a chicas adolescentes. Romance y relaciones. Ej: Sailor Moon, Fruits Basket"),
            new Genero("Seinen", "Para hombres adultos. Temas complejos y violentos. Ej: Berserk, Tokyo Ghoul"),
            new Genero("Josei", "Para mujeres adultas. Relaciones realistas. Ej: Nana, Paradise Kiss"),
            new Genero("Kodomo", "Para niños. Historias simples y educativas. Ej: Doraemon, Pokémon"),
            new Genero("Isekai", "Personaje transportado a otro mundo. Ej: Re:Zero, Sword Art Online"),
            new Genero("Mecha", "Robots gigantes. Ej: Gundam, Neon Genesis Evangelion"),
            new Genero("Deportes", "Competiciones deportivas. Ej: Haikyuu!!, Slam Dunk"),
            new Genero("Otro", "Otro género no listado")
    };

    public static void main(String[] args) {
        mostrarBienvenida();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            scanner.nextLine();

            switch(opcion) {
                case 1:
                    registrarManga();
                    break;
                case 2:
                    reseñarManga();
                    break;
                case 3:
                    mostrarReseñas();
                    break;
                case 4:
                    mostrarPromedios();
                    break;
                case 5:
                    System.out.println("\n¡Gracias por usar el sistema de reseñas de manga!");
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        } while(opcion != 5);
    }

    private static int leerOpcion() {
        while (true) {
            try {
                System.out.print("Seleccione una opción: ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Error: Debe ingresar un número. Intente nuevamente.");
                scanner.nextLine(); // Limpiar buffer
            }
        }
    }

    private static void mostrarBienvenida() {
        System.out.println("========================================");
        System.out.println("🌟 SISTEMA DE RESEÑAS DE MANGA 🌟");
        System.out.println("========================================");
        System.out.println("Autor: Pérez, Juan");
        System.out.println("Carrera: Programación I - 1er Año");
        System.out.println("Comisión: 12345");
        System.out.println("\nEste sistema permite:");
        System.out.println("- Registrar nuevos mangas");
        System.out.println("- Crear reseñas con puntuación de historia y dibujo");
        System.out.println("- Visualizar todas las reseñas registradas");
        System.out.println("- Calcular promedios de puntuaciones");
        System.out.println("========================================\n");
    }

    private static void mostrarMenu() {
        System.out.println("\n----- MENÚ PRINCIPAL -----");
        System.out.println("1. 📚 Registrar nuevo manga");
        System.out.println("2. ✍️  Reseñar un manga");
        System.out.println("3. 👀 Ver todas las reseñas");
        System.out.println("4. 📊 Ver promedios de mangas");
        System.out.println("5. ❌ Salir del sistema");
        System.out.print("Seleccione una opción: ");
    }

    private static void mostrarDescripcionesGeneros() {
        System.out.println("\n--- DESCRIPCIONES DE GÉNEROS ---");
        for (int i = 0; i < GENEROS.length; i++) {
            System.out.println("\n" + (i+1) + ". " + GENEROS[i].getNombre() + ":");
            System.out.println("   " + GENEROS[i].getDescripcion());
        }
        System.out.println();
    }

    private static void registrarManga() {
        if(contadorMangas >= MAX_MANGAS) {
            System.out.println("\n⚠️  Límite de mangas alcanzado. No se pueden registrar más.");
            return;
        }

        System.out.println("\n--- REGISTRO DE NUEVO MANGA ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        // Selección de género con menú interactivo
        int opcionGenero;
        String genero;
        do {
            System.out.println("\n--- SELECCIONE GÉNERO ---");
            for(int i = 0; i < GENEROS.length; i++) {
                System.out.println((i+1) + ". " + GENEROS[i].getNombre());
            }
            System.out.println("0. Ver descripciones de géneros");
            opcionGenero = leerOpcion();
            scanner.nextLine();

            if(opcionGenero == 0) {
                mostrarDescripcionesGeneros();
            } else if(opcionGenero > 0 && opcionGenero <= GENEROS.length) {
                genero = GENEROS[opcionGenero-1].getNombre();
                break;
            } else {
                System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        } while(true);

        mangas[contadorMangas] = new Manga(titulo, autor, genero);
        contadorMangas++;

        System.out.println("\n✅ ¡Manga registrado exitosamente!");
    }

    private static void reseñarManga() {
        if(contadorMangas == 0) {
            System.out.println("\n⚠️  Primero debe registrar al menos un manga.");
            return;
        }

        System.out.println("\n--- SELECCIONE UN MANGA PARA RESEÑAR ---");
        for(int i = 0; i < contadorMangas; i++) {
            System.out.println((i+1) + ". " + mangas[i].getInfoBasica());
        }

        System.out.print("\nIngrese el número del manga: ");
        int seleccion = leerOpcion();
        scanner.nextLine();

        if(seleccion < 1 || seleccion > contadorMangas) {
            System.out.println("❌ Selección inválida.");
            return;
        }

        Manga mangaSeleccionado = mangas[seleccion-1];

        System.out.println("\n--- RESEÑA PARA: " + mangaSeleccionado.getTitulo() + " ---");

        // Puntuación de historia
        System.out.print("Puntuación HISTORIA (1-5 estrellas): ");
        int estrellasHistoria = leerOpcion(); // Usamos lectura segura
        scanner.nextLine();  // Limpiar buffer

        if(estrellasHistoria < 1 || estrellasHistoria > 5) {
            System.out.println("❌ La puntuación debe ser entre 1 y 5 estrellas.");
            return;
        }

        // Puntuación de dibujo
        System.out.print("Puntuación DIBUJO (1-5 estrellas): ");
        int estrellasDibujo = leerOpcion(); // Usamos lectura segura
        scanner.nextLine();  // Limpiar buffer

        if(estrellasDibujo < 1 || estrellasDibujo > 5) {
            System.out.println("❌ La puntuación debe ser entre 1 y 5 estrellas.");
            return;
        }

        System.out.print("Comentario: ");
        String comentario = scanner.nextLine();

        reseñas[contadorReseñas] = new ReseñaManga(
                mangaSeleccionado, estrellasHistoria, estrellasDibujo, comentario
        );
        contadorReseñas++;

        System.out.println("\n✅ ¡Reseña registrada exitosamente!");
    }

    private static void mostrarReseñas() {
        if(contadorReseñas == 0) {
            System.out.println("\n⚠️  No hay reseñas registradas.");
            return;
        }

        System.out.println("\n--- TODAS LAS RESEÑAS ---");
        for(int i = 0; i < contadorReseñas; i++) {
            System.out.println("\n" + reseñas[i].getInfoCompleta());
            System.out.println("-----------------------------");
        }
    }

    private static void mostrarPromedios() {
        if(contadorMangas == 0) {
            System.out.println("\n⚠️  No hay mangas registrados.");
            return;
        }

        System.out.println("\n--- PROMEDIOS DE PUNTUACIÓN ---");
        for(int i = 0; i < contadorMangas; i++) {
            Manga manga = mangas[i];
            double sumaHistoria = 0;
            double sumaDibujo = 0;
            int contador = 0;

            for(int j = 0; j < contadorReseñas; j++) {
                if(reseñas[j].getManga().equals(manga)) {
                    sumaHistoria += reseñas[j].getPuntuacionHistoria();
                    sumaDibujo += reseñas[j].getPuntuacionDibujo();
                    contador++;
                }
            }

            System.out.println("\n" + manga.getTitulo() + ":");
            if(contador > 0) {
                double promedioHistoria = sumaHistoria / contador;
                double promedioDibujo = sumaDibujo / contador;
                System.out.printf("  HISTORIA: %.1f ★ (%d reseñas)%n", promedioHistoria, contador);
                System.out.printf("  DIBUJO: %.1f ★%n", promedioDibujo);
            } else {
                System.out.println("  Sin reseñas aún");
            }
        }
    }
}