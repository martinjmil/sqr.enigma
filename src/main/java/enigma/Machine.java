package enigma;

public class Machine {

        // attributs de la classe machine
	private Rotor leftRotor;
	private Rotor middleRotor;
	private Rotor rightRotor;
	private Reflector reflector;
        
        // Fonction d'initialisation de la place des rotors
	public void initRotors(Reflector reflector, Rotor left, Rotor middle, Rotor right) {
		this.reflector = reflector;
		leftRotor = left;
		middleRotor = middle;
		rightRotor = right;
	}

        // setter qui définie la position des rotors et du rélflecteur de la machine à partir d'une chaine de carractère
	public void setPositions(String setting) {
		char[] charSettings = setting.toCharArray();
		reflector.setPosition(Rotor.toIndex(charSettings[0]));
		leftRotor.setPosition(Rotor.toIndex(charSettings[1]));
		middleRotor.setPosition(Rotor.toIndex(charSettings[2]));
		rightRotor.setPosition(Rotor.toIndex(charSettings[3]));
	}
	// fonction qui définie la position des rotors avec la le setter "setPosition" après les avoir initialisé avec la fonction "initRotors"
	public void configure(Reflector reflector, Rotor left, Rotor middle, Rotor right, String setting) {
		this.initRotors(reflector, left, middle, right);
		this.setPositions(setting);

	}

        //fonction pour convertir le massage en tableau de charactères majuscules avant de retounre ce tableau
	public String convert(String msg) {
		msg = msg.toUpperCase();
		char[] msgChars = msg.toCharArray();
		String result = "";
		for (char c : msgChars) {
			result += convertChar(c);
		}
		return result;
	}
        // fonction qui chiffre le caractère du message en le faisant passer dans les rotors de la machine
	char convertChar(char c) {
		advanceRotors();
		int charIndex = Rotor.toIndex(c);
		int output;
		output = rightRotor.convertForward(charIndex);
		output = middleRotor.convertForward(output);
		output = leftRotor.convertForward(output);
		output = reflector.convertForward(output);
		output = leftRotor.convertBackward(output);
		output = middleRotor.convertBackward(output);
		output = rightRotor.convertBackward(output);
		return Rotor.toLetter(output);

	}
        //fonction qui modifie la position des rotors en la faisant avancer les rotors 
	void advanceRotors() {
		boolean advanceLeft = false;
		boolean advanceMiddle = false;
		boolean advanceRight = true;
                
                // tests qui vérifie la présent d'un notch pour avancer les rotors correspondant d'une possition
		if (leftRotor.atNotch()) {
		}
		if (middleRotor.atNotch()) {
			advanceMiddle = true;
			advanceLeft = true;
		}
		if (rightRotor.atNotch()) {
			advanceMiddle = true;
			advanceRight = true;
		}
                //fin des tests de la présence d'un notch
		if (advanceLeft) {
			leftRotor.advance();
		}
		if (advanceRight) {
			rightRotor.advance();
		}
		if (advanceMiddle) {
			middleRotor.advance();
		}
	}
}
