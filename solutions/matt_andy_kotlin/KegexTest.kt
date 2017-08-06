package co.abowes.regex

import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo

class KegexTest {

    @Test fun `given an empty regex check that it matches a string of 'a'`(){
        assertThat("".isMatch("a"), equalTo(true))
    }

    @Test fun `given a regex of 'a' check that it matches a string of 'a'`(){
        assertThat("a".isMatch("a"), equalTo(true))
    }

    @Test fun `given a regex of 'a' check that it doesn't match a string of 'b'`(){
        assertThat("a".isMatch("b"), equalTo(false))
    }

    @Test fun `given a regex of 'a' check that it matches a string of 'bacd'`(){
        assertThat("a".isMatch("bacd"), equalTo(true))
    }

    @Test fun `given a regex of 'a' check that it matches a string of 'cdba'`(){
        assertThat("a".isMatch("cdba"), equalTo(true))
    }

    @Test fun `given a regex of 'abc' check that it matches a string of 'abc'`(){
        assertThat("abc".isMatch("abc"), equalTo(true))
    }

    @Test fun `given a regex of 'abc' check that it doesn't match a string of 'acb'`(){
        assertThat("abc".isMatch("acb"), equalTo(false))
    }

    @Test fun `given a regex that is longer than the string it shouldn't match`(){
        assertThat("aa".isMatch("a"), equalTo(false))
    }

    @Test fun `given a regex which contains a wild character it should match any character`(){
        assertThat("a.b".isMatch("acb"), equalTo(true))
    }

     @Test fun `given a regex with wild characters should match axcye`(){  // a.c.e matches axcye
         assertThat("a.c.e".isMatch("axcye"), equalTo(true))
    }

    @Test fun `given a regex with wild characters shouldn't match abbbcde`(){  // a.c.e doesn't match abbbcde
        assertThat("a.c.e".isMatch("abbbcde"), equalTo(false))
    }

    @Test fun `given a regex with only wild characters should match axcye`(){  // ..... matches axcye
        assertThat(".....".isMatch("axcye"), equalTo(true))
    }

    @Test fun `given a regex with Word character should match axcye`(){  // ..... matches axcye
        assertThat("""a\wc\we""".isMatch("axcye"), equalTo(true))
    }

    @Test fun `given a regex with Word character should match axc9e`(){  // ..... matches axcye
        assertThat("""a\wc\we""".isMatch("axc9e"), equalTo(true))
    }

    @Test fun `given a regex with Digits character should not match axc9e`(){  // ..... matches axcye
        assertThat("""a\dc\de""".isMatch("axc9e"), equalTo(false))
    }

    @Test fun `given a regex with Digits character should match a7c9e`(){  // ..... matches axcye
        assertThat("""a\dc\de""".isMatch("a7c9e"), equalTo(true))
    }
}
