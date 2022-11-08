[<img src="https://3.bp.blogspot.com/-0WUVUcPxXh4/XJsF47nfOLI/AAAAAAAAFks/7yi8doOzsX8M7YbkDnyoq5wrAzYeCeV3ACLcBGAs/s1600/building%2Bblocks%2BTerri%2527s%2BTeaching%2BTreasures.png" width="250"/>](image.png)

# Syllable Counter

Syllable Counter counts the number of syllables of any one word the users has entered in the terminal. This project was made in Java. 


## Contributing

This project will not be worked on anymore and this is the final product. 

## Usage

```
usage: Syllables <English word>
```

Find the script's directory, mine was located in: Desktop\syllables-RamonAsuncion and run the script.
![GIF](http://g.recordit.co/0hEmXNujRK.gif)

## Terminal
```
$ java com/ramonasuncion/Syllables ok -d
'ok' has 1 syllable
```

## Algorithm 
* Count the number of vowels (*a*, *e*, *i*, *o*, *u*) in the word.
* Add 1 every time the letter *y* makes the sound of a vowel. This is when *y* is not the first letter in the word.
* Subtract 1 for each silent *e* at the end of a word.
* Subtract 1 for each diphthong or triphthong in the word (see below).
* Does the word end with *le* or *les?* If so, add 1 only if the letter *before* the *le* is a consonant
* If the word starts with *io*, add 1 to the syllable count.
* If the word ends with *ee* or *ie*, add 1 to the syllable count.
