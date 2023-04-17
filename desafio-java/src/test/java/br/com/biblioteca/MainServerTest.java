package br.com.biblioteca;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MainServerTest {
	
	@Test
	public void main() {
		MainServer.main(new String[] {});
	}
}
