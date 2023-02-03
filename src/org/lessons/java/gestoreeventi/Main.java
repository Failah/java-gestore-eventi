/*
 * Stiamo lavorando a un programma che deve gestire eventi (ad esempio concerti,
conferenze, spettacoli,...)
MILESTONE 1
La consegna è di creare una classe Evento che abbia le seguenti proprietà:
● titolo
● data
● numero di posti in totale
● numero di posti prenotati
Quando si istanzia un nuovo evento questi attributi devono essere tutti valorizzati nel
costruttore , tranne posti prenotati che va inizializzato a 0.
Inserire il controllo che la data non sia già passata e che il numero di posti totali sia positivo.
In caso contrario sollevare opportune eccezioni.
Aggiungere metodi getter e setter in modo che:
● titolo sia in lettura e in scrittura
● data sia in lettura e scrittura
● numero di posti totale sia solo in lettura
● numero di posti prenotati sia solo in lettura
Vanno inoltre implementati dei metodi public che svolgono le seguenti funzioni:
1. prenota : aggiunge uno ai posti prenotati. Se l’evento è già passato o non ha posti
disponibili deve sollevare un’eccezione.
2. disdici : riduce di uno i posti prenotati. Se l’evento è già passato o non ci sono
prenotazioni deve sollevare un’eccezione.
3. l’override del metodo toString() in modo che venga restituita una stringa
contenente: data formattata - titolo
Aggiungete eventuali metodi (public e private) che vi aiutino a svolgere le funzioni richieste.

 * MILESTONE 2
1. Creare una classe Main di test, in cui si chiede all’utente di inserire un nuovo evento
con tutti i parametri.
2. Dopo che l’evento è stato istanziato, chiedere all’utente se e quante prenotazioni
vuole fare e provare ad effettuarle, implementando opportuni controlli e gestendo
eventuali eccezioni.
3. Stampare a video il numero di posti prenotati e quelli disponibili
4. Chiedere all’utente se e quanti posti vuole disdire
5. Provare ad effettuare le disdette, implementando opportuni controlli e gestendo
eventuali eccezioni
6. Stampare a video il numero di posti prenotati e quelli disponibili

MILESTONE 3
Creare una classe Concerto che estende Evento, che ha anche gli attributi :
● ora: LocalTime
● prezzo: BigDecimal
Aggiungere questi attributi nel costruttore e implementarne getter e setter
Aggiungere i metodi per restituire data e ora formattata e prezzo formattato (##,##€)
Fare l’ override del metodo toString() in modo che venga restituita una stringa del tipo:
data e ora formattata - titolo - prezzo formattato
 */

package org.lessons.java.gestoreeventi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		// richiesta tipo di evento
		System.out.println("Vuoi creare un evento 'Evento' o un evento 'Concerto'?");
		String sceltaEvento = "";
		while (!sceltaEvento.equalsIgnoreCase("Evento") && !sceltaEvento.equalsIgnoreCase("Concerto")) {
			System.out.print("Inserisci 'Evento' o 'Concerto': ");
			sceltaEvento = s.nextLine();
		}

		// richiesta nome evento
		System.out.print("Inserisci il nome dell'evento: ");
		String titolo = s.nextLine();
		while (titolo.length() == 0) {
			System.out.println("Il nome non può essere vuoto! Inserisci il nome: ");
			titolo = s.nextLine();
		}

		// richiesta numero di posti
		System.out.print("Inserisci il numero totale di posti: ");
		int postiTotali = 0;
		boolean numeroValido = false;
		while (!numeroValido) {
			try {
				postiTotali = s.nextInt();
				s.nextLine();
				if (postiTotali <= 0) {
					System.out.println("I posti totali devono essere positivi! Riprova: ");
				} else {
					numeroValido = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Input non valido. Riprova: ");
				s.nextLine();
			}
		}

		// richiesta data dell'evento
		System.out.print("Inserisci la data dell'evento (gg/mm/aaaa): ");
		boolean dataValida = false;
		LocalDate data = null;
		while (!dataValida) {
			try {
				String dataInserita = s.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				data = LocalDate.parse(dataInserita, formatter);
				dataValida = true;
			} catch (DateTimeParseException e) {
				System.out.println("Data non valida. Riprova, usa questo formato -> (gg/mm/aaaa): ");
			}
		}

		// creazione dell'evento
		Evento evento;
		if (sceltaEvento.equalsIgnoreCase("Evento")) {
			evento = new Evento(titolo, data, postiTotali);
		} else {
			// richiesta orario del concerto
			System.out.println("Inserisci l'orario del concerto: ");
			boolean oraValida = false;
			LocalTime ora = null;
			while (!oraValida) {
				try {
					ora = LocalTime.parse(s.nextLine());
					oraValida = true;
				} catch (DateTimeParseException e) {
					System.out.println("Orario non valido. Riprova, usa questo formato -> (hh:mm): ");
				}
			}

			// richiesta prezzo del biglietto del concerto
			System.out.println("Inserisci prezzo del biglietto del concerto: ");
			boolean prezzoValido = false;
			BigDecimal prezzo = null;
			while (!prezzoValido) {
				try {
					prezzo = s.nextBigDecimal();
					if (prezzo.compareTo(BigDecimal.ZERO) <= 0) {
						throw new InputMismatchException();
					}
					prezzoValido = true;
				} catch (InputMismatchException e) {
					System.out.println("Prezzo non valido. Riprova: ");
					s.nextLine();
				}
			}
			evento = new Concerto(titolo, data, postiTotali, ora, prezzo);
		}

		// richiesta prenotazioni
		boolean prenotazioniValide = false;
		int prenotazioni = 0;
		while (!prenotazioniValide) {
			System.out.print("Quante prenotazioni vuoi effettuare? ");
			try {
				prenotazioni = s.nextInt();
				s.nextLine();
				prenotazioniValide = true;
			} catch (InputMismatchException e) {
				System.out.println(
						"Input non valido, inserisci un numero positivo e non superiore ai posti disponibili. Riprova: ");
				s.nextLine();
			}
		}
		for (int i = 0; i < prenotazioni; i++) {
			evento.prenota();
		}

		System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
		System.out.println("Posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));

		// gestione eventuali disdette
		System.out.println("Vuoi effettuare delle disdette? (s/n)");
		String risposta = s.nextLine();
		if (risposta.equalsIgnoreCase("s")) {
			System.out.println("Quante disdette vuoi effettuare?");
			int disdette = 0;
			boolean disdetteValide = false;
			while (!disdetteValide) {
				try {
					disdette = s.nextInt();
					disdetteValide = true;
				} catch (InputMismatchException e) {
					System.out.println("Input non valido. Riprova: ");
					s.nextLine();
				}
			}
			for (int i = 0; i < disdette; i++) {
				evento.disdici();
			}
		}

		System.out.println(evento.toString());
		System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
		System.out.println("Posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));
		s.close();
	}
}
