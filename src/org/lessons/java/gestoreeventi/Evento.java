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
 */

/*
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
 */

package org.lessons.java.gestoreeventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Evento {
	private String titolo;
	private LocalDate data;
	private int postiTotali;
	private int postiPrenotati = 0;

	public Evento(String titolo, LocalDate data, int postiTotali) {
		super();
		this.titolo = titolo;
		setData(data);

		// controllo inserimento posti totali
		if (postiTotali <= 0) {
			throw new IllegalArgumentException("I posti totali devono essere positivi!");
		}
		this.postiTotali = postiTotali;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		LocalDate now = LocalDate.now();
		if (data.isBefore(now)) {
			throw new IllegalArgumentException("Non puoi inserire una data già trascorsa!");
		}
		this.data = data;
	}

	public int getPostiTotali() {
		return postiTotali;
	}

	public int getPostiPrenotati() {
		return postiPrenotati;
	}

	public void prenota() {
		try {
			LocalDate now = LocalDate.now();
			if (data.isBefore(now)) {
				throw new IllegalStateException("Non puoi prenotare un evento già passato!");
			}
			if (postiPrenotati >= postiTotali) {
				throw new IllegalStateException(
						"Non puoi prenotare più posti di quelli disponibili! Riprova, i posti disponibili sono "
								+ this.postiTotali + ": ");
			}
			postiPrenotati++;
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		}
	}

	public void disdici(int disdette) {
		LocalDate now = LocalDate.now();
		if (data.isBefore(now)) {
			throw new IllegalStateException("Non puoi disdire un evento già passato!");
		}
		if (postiPrenotati < disdette) {
			throw new IllegalStateException(
					"Non puoi effettuare più disdette dei posti prenotati! I posti prenotati sono "
							+ getPostiPrenotati() + ": ");
		}
		postiPrenotati -= disdette;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.ITALY);
		String formattedDate = data.format(formatter);
		return "Data dell'evento: " + formattedDate + " - " + "Nome dell'evento: " + titolo;
	}

}
