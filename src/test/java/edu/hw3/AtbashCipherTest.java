package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AtbashCipherTest {
    @Test
    @DisplayName("Тест шифрования прописных букв")
    public void smallLettersTest() {
        String str = "abcdefghijklmnopqrstuvwxyz";

        AtbashCipher obj = new AtbashCipher();
        String result = obj.encode(str);

        assertThat(result).isEqualTo("zyxwvutsrqponmlkjihgfedcba");
    }

    @Test
    @DisplayName("Тест шифрования заглавных букв")
    public void bigLettersTest() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        AtbashCipher obj = new AtbashCipher();
        String result = obj.encode(str);

        assertThat(result).isEqualTo("ZYXWVUTSRQPONMLKJIHGFEDCBA");
    }

    @Test
    @DisplayName("Тест шифрования остальных символов")
    public void otherCharactersTest() {
        String str = "1234567890-=!@#$%^&*()_+,./;'|:";

        AtbashCipher obj = new AtbashCipher();
        String result = obj.encode(str);

        assertThat(result).isEqualTo(str);
    }

}
