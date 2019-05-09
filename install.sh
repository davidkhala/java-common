#!/usr/bin/env bash
java11() {
	#TODO this is for ubuntu only
	sudo add-apt-repository -y ppa:linuxuprising/java
	sudo apt update
	sudo apt install -y oracle-java11-installer
	sudo apt install -y oracle-java11-set-default
}