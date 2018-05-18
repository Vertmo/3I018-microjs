# microjs #
Le compilateur pour un langage (type js) écrit dans l'UE 3I018 à l'UPMC (en 2018).

### Contenu ###
* Le compilateur (implémenté en Java) dans le dossier */JCompiler*
* La machine virtuelle (implémentée en C) dans le dossier */NativeVM*

### Utilisation ###
* `java -jar JCompiler/jcompiler.java tests/<fichier>.js` pour compiler *<fichier>.js*; le compilateur a pleins d'options (seulement parser ou afficher le kast par exemple) qui sont affichables avec `java -jar jcompiler.java -help`.
* `./NativeVM/runvm tests/<fichier>.js.bc` pour exécuter le bytecode contenu dans *<fichier>.js.bc*.

## Licence ##
Ce projet est sous licence GPL v3.0. 
Projet original : (C) 2009-2011 Equipe enseignante de la licence informatique de l'UPMC sous licence GPL v2.0
