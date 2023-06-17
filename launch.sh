#!/bin/bash

# Fonction récursive pour supprimer les fichiers "*.class"
delete_class_files() {
    for file in "$1"/*; do
        if [ -d "$file" ]; then
            delete_class_files "$file"  # Appel récursif pour les sous-dossiers
        elif [ "${file##*.}" = "class" ]; then
            rm "$file"
        fi
    done
}

# Entrer dans le dossier "source/"
cd source/

# Supprimer les fichiers "*.class" récursivement
delete_class_files .

# Revenir à la racine
cd ..

# Compiler le fichier "SimulatorMainGUI.java"
javac source/view/SimulatorMainGUI.java

# On netttoie le terminal
clear

# Exécuter la classe "SimulatorMainGUI"
java source.view.SimulatorMainGUI

# Supprimer les fichiers "*.class" récursivement
delete_class_files .