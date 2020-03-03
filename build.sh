#!/bin/bash

javaMainClass="taquin.Main"
jarName="Taquin"
zipName="taquin.zip"

buildDir="build"
deployDir="dist"
javadocDir="doc"
libsDir="libs"
srcDir="src"
testDir="test"
resDir="res"

projectBuilded=false

# Test

zipProject() {
	echo '>zip'	
	cleanProject
	createJavadoc
	deployProject
	
	rm -rf $buildDir
	
	zip $zipName *
}

showCommand() {
	echo "Usage : $0 <build|clean|deploy|javadoc|run|test|zip>" 1>&2
	exit 1
}

buildProject() {
	if [ "$projectBuilded" = true ] ; then
		return
	fi

	echo '>build'

	if [ ! -d $buildDir ]; then
		mkdir $buildDir
	fi

	updateLibs
	javac -cp "$srcDir$libs" -d $buildDir $srcDir/$(echo $javaMainClass | sed -E "s/\./\//g").java -Xlint

	if [ ! $? -eq 0 ]; then
		exit 2
	fi
	
	projectBuilded=true
}

cleanProject() {
	echo '>clean'

	if [ -d $buildDir ]; then
		rm -rf $buildDir
	fi
	
	if [ -d $deployDir ]; then
		rm -rf $deployDir
	fi
	
	if [ -d $javadocDir ]; then
		rm -rf $javadocDir
	fi

	if [ -f $zipName ]; then
		rm $zipName
	fi
	
	projectBuilded=false
}

createJavadoc() {
	echo '>javadoc'

	if [ ! -d $javadocDir ]; then
		mkdir $javadocDir
	fi
	
	updateLibs
	javadoc -subpackages $(echo $javaMainClass | sed -E "s/([^.]*)\..*/\1/") -private -d $javadocDir -cp "$srcDir$libs"
}


deployProject() {
	buildProject

	if [ -d $deployDir ]; then
		rm -rf $deployDir
	fi
	
	echo '>deploy'
	mkdir $deployDir
	
	tmpManifest="/tmp/MANIFEST.MF"
	if [ -f $tmpManifest ]; then
		rm $tmpManifest
	fi
	
	echo "Main-Class: $javaMainClass" >> $tmpManifest
	echo "Class-Path: $(find libs -name '*.jar' -printf '%p ')" >> $tmpManifest
	
	cd $buildDir
	jar cfm ../$deployDir/$jarName.jar $tmpManifest $(find . -name '*.class')
	cd ..
	
	if [ -d $resDir ]; then
		cp $resDir $deployDir -r
	fi
	
	if [ -d $libsDir ]; then
		cp $libsDir $deployDir -r
	fi
}

runProject() {
	buildProject
	echo '>run'

	updateLibs
	java -cp "$buildDir:$libs" $javaMainClass $*
}

testProject() {
	buildProject

	echo '>test'
	
	updateLibs
	java -cp "$buildDir$libs" edt.Test
}

updateLibs() {
	libs=""
	if [ -d $libsDir ]; then
		libs=$(find libs -name '*.jar' -printf ':%p')
	fi
}

if [ $# -lt 1 ]; then
	showCommand
fi

while [ ! -z $1 ]
do
	if [ $1 = "build" ]; then
		buildProject
	elif [ $1 = "clean" ]; then
		cleanProject
	elif [ $1 = "deploy" ]; then
		deployProject
	elif [ $1 = "javadoc" ]; then
		createJavadoc
	elif [ $1 = "run" ]; then
		args=""
		if [ ! -z $2 ] && [ "$2" = "--args" ]; then
			args=$3
			shift
			shift
		fi

		runProject $args
	elif [ $1 = "test" ]; then
		testProject
	elif [ $1 = "zip" ]; then
		zipProject
	else
		showCommand
	fi

	shift
done
