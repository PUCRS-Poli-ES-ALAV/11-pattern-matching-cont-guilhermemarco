/*
	Complexidade: O(n)
*/

class KMP_String_Matching { 
	
	int cont;

    public KMP_String_Matching(){
		this.cont = 0;
	}

    void KMPSearch(String pat, String txt) 
	{ 
		int M = pat.length(); 
		int N = txt.length();
		this.cont += 4; 

		// cria lps[] que vai guardar o maior 
		// valor prefixo sufixo para o padrão 
		int lps[] = new int[M]; 
		int j = 0; // index for pat[] 
		this.cont += 4;

		// Calcula lps[] 
		computeLPSArray(pat, M, lps); 
		this.cont += 1;

		int i = 0; // index for txt[] 
		this.cont += 2;

		while (i < N) { 
			this.cont += 1;

			if (pat.charAt(j) == txt.charAt(i)) { 
				j++; 
				i++;
				this.cont += 7; 
			} 
			if (j == M) { 
				//System.out.println("Found pattern "
				//				+ "at index " + (i - j)); 
				j = lps[j - 1];
				this.cont += 3; 
			} 

			// mismatch após j matches 
			else if (i < N && pat.charAt(j) != txt.charAt(i)) { 
				// Não faz match dos caracteres lps[0..lps[j-1]], 
				// não é necesssário, eles combinarão 
				if (j != 0){
					j = lps[j - 1];
					this.cont += 4;
				} 
				else{
					i = i + 1;	
					this.cont += 2;
				} 
			} 
		} 
	} 

	void computeLPSArray(String pat, int M, int lps[]) 
	{ 
		// tamanho do maior prefixo sufixo anterior 
		int len = 0; 
		int i = 1; 
		lps[0] = 0; // lps[0] is always 0 
		this.cont += 7;

		// loop calcula lps[i] for i = 1 to M-1 
		while (i < M) {
			this.cont++; 
			if (pat.charAt(i) == pat.charAt(len)) { 
				len++; 
				lps[i] = len; 
				i++;
				this.cont += 9; 
			} 
			else // (pat[i] != pat[len]) 
			{ 
				if (len != 0) { 
					len = lps[len - 1];
					this.cont += 4;  
				} 
				else // if (len == 0) 
				{ 
					lps[i] = len; 
					i++;
					this.cont += 4; 
				} 
			} 
		} 
	}
	
	public String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    }
	
	public int getInstrucoes(){
		return this.cont;
	}

    public static void main(String args[]){

        String pat = "lhe";
        KMP_String_Matching a = new KMP_String_Matching();
        String txt = a.getAlphaNumericString(550000) + "lhe";
        
		a.KMPSearch(pat, txt);
		System.out.println("Instruções: " + a.getInstrucoes());
    }
}    