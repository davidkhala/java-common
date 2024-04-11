#!/usr/bin/env bash
java11() {
	sudo add-apt-repository -y ppa:linuxuprising/java
	sudo apt update

	sudo apt install -y oracle-java11-installer-local
	# Interaction required: You must agree to the license...
	sudo apt install -y oracle-java11-set-default-local
}
$@