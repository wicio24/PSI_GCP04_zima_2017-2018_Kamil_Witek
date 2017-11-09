#pragma once
#include "Perceptron.h"

using namespace std;

void wczytaj_wzornik(int tab[20][24]);
void wypisz(int tab[20][24]);
void testuj(int tab[20][24], Perceptron per);

void main()
{
	setlocale(LC_ALL, "");
	Perceptron nowy;
	int tab[20][24];

	wczytaj_wzornik(tab);
	nowy.losuj_wagi();
	nowy.Widrow_Hoff();
	//	wypisz(tab);
	testuj(tab, nowy);

	system("PAUSE");
}

/* WZORNIK z danych uczacych
0,1,1,0,1,0,0,1,1,0,0,1,1,1,1,1,1,0,0,1,1,0,0,1	 	A
0,0,0,0,0,0,0,0,0,1,0,0,1,0,1,0,1,0,1,0,0,1,0,1   	a
1,1,1,0,1,0,0,1,1,1,1,0,1,0,0,1,1,0,0,1,1,1,1,0		B
1,0,0,0,1,0,0,0,1,1,1,0,1,0,0,1,1,0,0,1,1,1,1,0		b
1,1,1,1,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,1,1,1		C
0,0,0,0,0,0,0,0,1,1,1,0,1,0,0,0,1,0,0,0,1,1,1,0		c
1,1,1,0,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,1,1,0 	D
0,0,0,1,0,0,0,1,0,1,1,1,1,0,0,1,1,0,0,1,0,1,1,1		d
1,1,1,1,1,0,0,0,1,1,1,1,1,0,0,0,1,0,0,0,1,1,1,1 	E
0,0,0,0,1,1,1,0,1,0,0,1,1,1,1,0,1,0,0,0,1,1,1,1 	e
1,1,1,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1,1,1,1 	Z
0,0,0,0,0,0,0,0,1,1,1,1,1,0,1,0,0,1,0,0,1,1,1,1		z
1,1,1,0,1,0,0,1,1,1,1,0,1,0,0,1,1,0,0,1,1,0,0,1		R
0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,0,0,0,1,0,0,0 	r
1,0,0,1,1,0,1,0,1,1,0,0,1,1,0,0,1,0,1,0,1,0,0,1 	K
1,0,0,0,1,0,0,0,1,0,1,0,1,1,0,0,1,1,0,0,1,0,1,0 	k
1,0,0,1,1,0,0,1,1,1,1,1,1,0,0,1,1,0,0,1,1,0,0,1 	H
1,0,0,0,1,0,0,0,1,0,0,0,1,1,1,0,1,0,0,1,1,0,0,1		h
1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,1,1,1 	L
1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0		l
0,1,1,0,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,0,1,1,0 	O
0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,1,1,0,0,1,0,1,1,0 	o
*/

void wczytaj_wzornik(int tab[20][24])
{
	cout << "Wczytywanie wzornika do testów..." << endl;
	ifstream odczyt;	//utworzenie zmiennej ifstream w celu uzyskania dostepu do pliku
	odczyt.open("wzornik.txt");	//otwiera plik w katalogu domyslnym o nazwie "test1.txt"
	if (!odczyt.good()) //jesli nie mozna otworzyc pliku to wypisz blad
	{
		cerr << "Nie mo¿na otworzyæ pliku Ÿród³owego!" << endl
			<< "blad #" << odczyt.fail() << endl << endl;
		system("PAUSE");
		exit(1);	//jesli nie udalo sie otworzyc pliku to zakoncza dzialanie calego programu
	}
	while (!odczyt.eof()) // dopoki sa dane w pliku, wczytuj dane
	{
		for (int i = 0; i < 20; i++)
		{
			for (int j = 0; j < 24; j++)
			{
				odczyt >> tab[i][j]; //wczytaj dane do tetów
			}
		}
		cout << "Odczyt z pliku zakoñcony powodzeniem!" << endl;
	}
	odczyt.close(); // zamknij plik
}

void wypisz(int tab[20][24])
{
	for (int i = 0; i < 20; i++)
	{
		for (int j = 0; j < 24; j++)
		{
			cout << tab[i][j] << ", ";
		}
		cout << endl;
	}
}

void testuj(int tab[20][24], Perceptron per)
{
	cout << "Testowanie danych..." << endl << endl;
	int pom[24];
	for (int i = 0; i < 20; i++)
	{
		for (int j = 0; j < 24; j++)
		{
			pom[j] = tab[i][j];
		}

		per.sprawdz(pom);
	}

}