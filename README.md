# MP08: Bit Trees and Braille

## Groups
```
Seunghyeon Kim
```

## Description
```
I have created a program that behaves as a braille to string converter. It takes in
input from the commandline, and depending on the option it converts the input to
another output that is desired by the user.
```

## Files
```
ASCIIToBraille.txt
BitTree.java
BitTreeNode.java
BrailleASCIITables.java
BrailleToASCII.txt
BrailleToUniCode.txt
README.md
```

## How to Compile
```
javac -d bin/ *.java
```

## How to Run
```
java -cp bin BrailleASCII <option> <input>

<option> ∈ {"ascii", "braille", "unicode"}
"ascii" : converts braille to ASCII
"braille" : converts ASCII to braille
"unicode" : converts braille to unicode

<input> can be any valid input for the desired conversion option.
```

## Acknowledgements
```
Professor Sam. Rebelsky
https://rebelsky.cs.grinnell.edu/Courses/CSC207/2023Fa/labs/binary-search-trees.html
https://rebelsky.cs.grinnell.edu/Courses/CSC207/2023Fa/labs/binary-search-trees-continued.html
https://rebelsky.cs.grinnell.edu/Courses/CSC207/2023Fa/mps/mp08.html
https://rebelsky.cs.grinnell.edu/Courses/CSC207/2023Fa/labs/tree-traversal.html
```
