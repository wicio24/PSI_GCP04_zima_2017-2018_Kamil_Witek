#pragma once
#include "Perceptron.h"

Perceptron::Perceptron()
{
	this->ilosc_wejsc = 10; //ustawienie wynika z przygotowanych danych uczacych
	this->wspolczynnik_nauki = 0.05;

	wczytanie_danych();	//wczytanie danych z pliku tekstowego
}

void Perceptron::wczytanie_danych()
{
	ifstream plik;	//utworzenie zmiennej ifstream w celu uzyskania dostepu do pliku
	plik.open("test1.txt");	//otwiera plik w katalogu domyslnym o nazwie "test1.txt"
	if (!plik.good()) //jesli nie mozna otworzyc pliku to wypisz blad
	{
		cerr << "Nie mo¿na otworzyæ pliku Ÿród³owego!" << endl
			<< "blad #" << plik.fail() << endl << endl;
		system("PAUSE");
		exit(1);	//jesli nie udalo sie otworzyc pliku to zakoncza dzialanie calego programu
	}
	while (!plik.eof()) // dopoki sa dane w pliku, wczytuj dane
	{
		for (int i = 0; i < ilosc_wejsc; i++)
		{
			for (int j = 0; j < 24; j++)
			{
				plik >> this->tab_wej[i][j]; //wczytaj dane do tablicy z wejsciami
			}
			plik >> this->tab_wyn_ocz[i]; //wczytaj wynik oczekiwany
		}
	}
	plik.close(); // zamknij plik
}

void Perceptron::losuj_wagi()
{
	for (int i = 0; i < 24; i++)
	{
		this->tab_wag[i] = (float)rand() / (float)RAND_MAX; //dla kazdej wagi perceptronu losuje wartosc z zakresu od 0 do 1
	}
}

float Perceptron::licz_wyjscia(int i)
{
	/*funkcja sumujaca perceptronu
	za argument przyjmuje indeks danej z tab_wej[][], ktora jest teraz wykorzystywana do uczenia.
	Nastepnie oblicza sume dla przyjetej danej(skladajacej sie z 24 elementow)
	*/
	float suma = 0;
	for (int j = 0; j < 24; j++)
	{
		suma += this->tab_wej[i][j] * this->tab_wag[j];
	}
	return suma;
}

void Perceptron::Widrow_Hoff()
{
	float blad_lokalny;	//zmienna do obliczania bledu lokalnego
	float blad_globalny = 0;	//zmienna do oblioczania bledu globalnego, musi byc zainicjowana!
	int epoka = 0;	//liczba epok nauczania
	int przypadek;	//indek elementu tab_wej, ktory jest obecnie rozpatrywany
	float wynik_tmp;	//przechowuje tymczasowy wynik, ktory jest obliczany w kazdej iteracji
	float prog = 1.55; //zmienna, ktora okresla, kiedy algorytm jest juz wystaczajaco nauczony

	do
	{
		blad_globalny = 0;
		for (przypadek = 0; przypadek < this->ilosc_wejsc; przypadek++)
		{
			/*OBLICZANIE WYNIKU W ITERACJI */
			wynik_tmp = licz_wyjscia(przypadek);
			blad_lokalny = this->tab_wyn_ocz[przypadek] - wynik_tmp; //obliczanie bledu lokalnego w tej iteracji petli for

																	 /*AKTALIZACJA WAG*/

			for (int i = 0; i < 24; i++)
			{
				this->tab_wag[i] += this->wspolczynnik_nauki * blad_lokalny * this->tab_wej[przypadek][i];
			}

			blad_globalny += (blad_lokalny*blad_lokalny); //obliczanie bledu globalnego, warunku wyjscia z petli do...while();
		}
		epoka++;
	} while (blad_globalny > prog);  //powtarza dopoki blad globalny jest wiekszy od zadanego progu
	cout << "Liczba wykonanych epok = " << epoka << endl;
}

void Perceptron::sprawdz(int tab[])
{
	/*Za argument przyjmuje tablice z jedna dana testujaca(zawierajaco 24 bity)*/
	float suma = 0, blad_lokalny = 0, blad_globalny = 0, prog_aktywacji = 1.5;
	for (int i = 0; i < 24; i++)
	{
		suma += tab[i] * this->tab_wag[i]; //oblicza sume dla zadanego elementu
	}
	blad_lokalny = 1 - suma; //oblicza blad lokalny dla zadanego elementu
	blad_globalny = pow(2, blad_lokalny);	//blad globalny dla zadanego elementu

	if (blad_globalny < prog_aktywacji)	//jesli blad globalny jest mniejszy od naszego progu aktuwacji 
		this->wynik = true;	//ustawia wynik na true
	else
		this->wynik = false; //ustawia wynik na false

	wypisz(); //wypisz w konsoli wynik sprawdzenia
}

void Perceptron::wypisz()
{
	if (this->wynik == true)
		cout << "Podana jest ma³a litera!" << endl;
	else
		cout << "Podana jest du¿a litera!" << endl;
}

Perceptron::~Perceptron()
{
	
}