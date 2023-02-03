/*
 * MILESTONE 4 (bonus)
Creare una classe ProgrammEventi con i seguenti attributi
● titolo: String
● eventi: List<Evento>
Nel costruttore valorizzare il titolo, passato come parametro, e inizializzare la lista di eventi
come una nuova ArrayList
Aggiungere i seguenti metodi :
● un metodo che aggiunge alla lista un Evento, passato come parametro
● un metodo che restituisce una lista con tutti gli eventi presenti in una certa data
● un metodo che restituisce quanti eventi sono presenti nel programma
● un metodo che svuota la lista di eventi
● un metodo che restituisce una stringa che mostra il titolo del programma e tutti gli
eventi ordinati per data nella forma:
○ data1 - titolo1
○ data2 - titolo2
○ data3 - titolo3
 */

package org.lessons.java.gestoreeventi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProgrammEventi {
	private String titolo;
	private List<Evento> eventi;

	public ProgrammEventi(String titolo) {
		super();
		this.titolo = titolo;
		this.eventi = new ArrayList<Evento>();
	}

	public void aggiungiEvento(Evento evento) {
		this.eventi.add(evento);
	}

	public List<Evento> ottieniEventiInData(LocalDate dataCheck) {
		List<Evento> eventiInDataCheck = new ArrayList<Evento>();
		for (Evento evento : this.eventi) {
			if (evento.getData().equals(dataCheck)) {
				eventiInDataCheck.add(evento);
			}
		}
		return eventiInDataCheck;
	}

	public int numDiEventi() {
		return this.eventi.size();
	}

	public void svuotaListaEventi() {
		this.eventi.clear();
	}

	public String programInfo() {

		// ordinare per data
		Collections.sort(this.eventi, new Comparator<Evento>() {
			public int compare(Evento e1, Evento e2) {
				return e1.getData().compareTo(e2.getData());
			}
		});

		// usare string builder per creare la stringa da ritornare
		// provare il metodo .append()
		StringBuilder stringaBuildata = new StringBuilder();
		stringaBuildata.append(this.titolo + "\n");

		for (Evento evento : this.eventi) {
			stringaBuildata.append(evento.getData() + " - " + evento.getTitolo() + "\n");
		}
		return stringaBuildata.toString();
	}

}
