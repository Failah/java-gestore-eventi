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
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		System.out.println("Vuoi creare un evento 'Evento' o un evento 'Concerto'?");
		System.out.print("Inserisci 'Evento' o 'Concerto': ");
		String sceltaEvento = s.nextLine();

		System.out.print("Inserisci il nome dell'evento: ");
		String titolo = s.nextLine();

		System.out.print("Inserisci il numero totale di posti: ");
		int postiTotali = s.nextInt();
		s.nextLine();

		System.out.print("Inserisci la data dell'evento (gg/mm/aaaa): ");
		String dataInserita = s.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dataInserita, formatter);

		// creazione dell'evento
		Evento evento;
		if (sceltaEvento.equalsIgnoreCase("Evento")) {
			evento = new Evento(titolo, data, postiTotali);
		} else {
			System.out.println("Inserisci l'orario del concerto: ");
			LocalTime ora = LocalTime.parse(s.nextLine());
			System.out.println("Inserisci prezzo del biglietto del concerto: ");
			BigDecimal prezzo = s.nextBigDecimal();
			evento = new Concerto(titolo, data, postiTotali, ora, prezzo);
		}

		System.out.print("Quante prenotazioni vuoi effettuare? ");
		int prenotazioni = s.nextInt();
		s.nextLine();

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
			int disdette = s.nextInt();

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
