#include <iostream>
#include <fstream>
#include <string>
//#include <windows.h>
//#include <mmsystem.h>
#include <vector>
//#include <Mmsystem.h>
//#include <mciapi.h>
//these two headers are already included in the <Windows.h> header
//#pragma comment(lib, "Winmm.lib")
using namespace std;
int main()
{
    string input = "";    
    getline (cin,input);
	string phrase = "";
	phrase = input;
	int phrasecharlength = phrase.length();
	int phrasepos = 0;
	int wordsphrase = 1;
	string phrasechar;
	while (phrasecharlength > 0) {
		phrasechar = phrase[phrasepos];
		if (phrasechar == " ") {
			wordsphrase += 1;
		}
		phrasecharlength -= 1;
		phrasepos += 1;
	}
	//finding how many words are in the phrase
	string word = "";
	vector<string> words;
	phrasecharlength = phrase.length() + 1;
	phrase += " ";
	phrasepos = 0;
	int wordlength;
	while (phrasecharlength > 0) {
		phrasechar = phrase[phrasepos];
		word += phrasechar;
		if (phrasechar == " ") {
			wordlength = word.length() - 1;
			word.erase(wordlength, 1);
			words.push_back(word);
			word = "";
		}
		phrasecharlength -= 1;
		phrasepos += 1;
	}
	//storing the words in the phrase into a vector
	int numwords = words.size();
	int vectorpos = 0;
	int linepos = 0;
	int linelength;
	int linedelete = 0;
	string linechar;
	string syllable;
	ifstream wordBase;
	string line;
	vector<string> syllables;
	while (numwords > 0) {
		wordBase.open("cmudict.txt");
		word = " " + words[vectorpos] + " ";
		wordlength = word.length() + 1;
		linepos = 0;
		while (line.find(word) == string::npos) {
			getline(wordBase, line);
		}
		wordBase.close();
		line.erase(0, wordlength);
		line += " "; //good up until here
		linelength = line.length();
		while (linelength > 0) {
			linechar = line[linepos];
			syllable += linechar;
			if (linechar == " ") {
				linedelete = syllable.length()-1;
				syllable.erase(linedelete, 1);
				if (linedelete == 3) {
					linedelete -= 1;
					if (syllable[linedelete] == '0' or syllable[linedelete] == '1' or syllable[linedelete] == '3') {
						syllable.erase(linedelete, 1);
					}
				}
				syllables.push_back(syllable);
				cout << syllable << endl;
				syllable = "";
			}
			linedelete += 1;
			linepos += 1;
			linelength -= 1;
		}
		syllables.push_back("PAUSE");
		vectorpos += 1;
		numwords -= 1;
	}
	//changing word vector into syllable / pause vector
	vector<string> wavfiles;
	int loopnum = syllables.size();
	vectorpos = 0;
	string wavfile;
	while (loopnum > 0) {
		wavfile = syllables[vectorpos] + ".wav";
		wavfiles.push_back(wavfile);
		cout << wavfile << endl;
		vectorpos += 1;
		loopnum -= 1;
	}
	//changing word/pause vector to wav files
	loopnum = wavfiles.size();
	vector<wstring> wstringwavs;
	string str;
	vectorpos = 0;
	while (loopnum > 0) {
		str = wavfiles[vectorpos];
		wstring wstr(str.begin(), str.end());
		wstringwavs.push_back(wstr);
		vectorpos += 1;
		loopnum -= 1;
	}
	/////turning strings into wstrings ig
	//loopnum = wavfiles.size();
	//vectorpos = 0;
	//wstring file;
	//while (loopnum > 0) {
	//	file = wstringwavs[vectorpos];
	//	PlaySound(file.c_str(), NULL, SND_FILENAME | SND_ASYNC);
	//	vectorpos += 1;
	//	loopnum -= 1;
	//}
	//playing sound NEED TO FIX!!!!!!!!!!!! GET HELP?? FIRE NATION ATTACK
}
