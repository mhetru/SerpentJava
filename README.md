Serpent : french game in JAVA
---

What else's fun to learn programmation oriented objects with game?

It was a personal game at 2003, finished in 2019.

Pre-requisite :
- JDK (OpenJDK or Oracle JDK)

---

* Graphic version of the game :

1. to compile the game :
```
cd sources
javac -encoding ISO-8859-15 serpent/Serpent.java -d ../classes
```

2. to launch the game :
```
cd classes
java serpent/Serpent
```

* To create the documentation :
```
cd sources
javadoc -encoding ISO-8859-15 -version -author *.java -package serpent serpent.interf serpent.objet -d ../docs
```

* Browse the documentation :

See the index.html file in docs folder.
