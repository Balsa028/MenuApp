package com.balsa.menuapp.Utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UtilTest {

    @Mock
    private Context context;
    @Mock
    private SharedPreferences preferences;
    @Mock
    private SharedPreferences.Editor editor;

    @BeforeAll
    void init(){
        MockitoAnnotations.initMocks(this);
        when(context.getApplicationContext()).thenReturn(context);
        when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(preferences);
        when(preferences.edit()).thenReturn(editor);
    }

    @Test
    void goodUserTokenInput(){
        Util.saveTokenInSharedPrefs("balsa", context);
        assertEquals("balsa",   Util.readTokenFromSharedPrefs(context));
    }

    @Test
    void nullContextInputInSavingTokenInPrefs(){
        Assertions.assertThrows(NullPointerException.class, () ->{
            Util.saveTokenInSharedPrefs("balsa", null);
        });
    }

    @Test
    void nullContextInputInReadingTokenFromPrefs(){
         when(preferences.getString(anyString(), anyString())).thenReturn("balsa");
         Assertions.assertThrows(NullPointerException.class, () ->{
             Util.readTokenFromSharedPrefs(null);
         });
    }

}