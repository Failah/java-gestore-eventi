/*
 * MILESTONE 3
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
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Concerto extends Evento {
	private LocalTime ora;
	private BigDecimal prezzo;

	public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, BigDecimal prezzo) {
		super(titolo, data, postiTotali);
		setOra(ora);
		setPrezzo(prezzo);
	}

	public LocalTime getOra() {
		return ora;
	}

	public String getDataOra() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withLocale(Locale.ITALY);
		return getData().format(formatter) + " " + ora.toString();
	}

	public void setOra(LocalTime ora) {
		this.ora = LocalTime.now();
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public String getPrezzoFormattato() {
		return NumberFormat.getCurrencyInstance(Locale.ITALY).format(prezzo);
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public String toString() {
		return getDataOra() + " " + getTitolo() + " " + getPrezzoFormattato();
	}

}
