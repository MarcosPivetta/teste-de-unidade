package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTest {

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Macbook Pro15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000.0));
		
		//Verifica se o tamanho da lista é igual a 1
		assertEquals(1, leilao.getLances().size());
		
		//Verifica se o valor do lance é igual a 2000.0
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Macbook Pro15");

		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000));

		//Verifica se o tamanho da lista é igual a 2
		assertEquals(2, leilao.getLances().size());
		
		//Verifica se o valor do lance é igual a 2000.0 e 3000.0
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(steveJobs, 3000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.0001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		Usuario billgates = new Usuario("Bill Gates");

		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(billgates, 3000));
		
		leilao.propoe(new Lance(steveJobs, 4000));
		leilao.propoe(new Lance(billgates, 5000));
		
		leilao.propoe(new Lance(steveJobs, 6000));
		leilao.propoe(new Lance(billgates, 7000));
		
		leilao.propoe(new Lance(steveJobs, 8000));
		leilao.propoe(new Lance(billgates, 9000));
		
		leilao.propoe(new Lance(steveJobs, 10000));
		leilao.propoe(new Lance(billgates, 11000));
		
		// deve ser ignorado
		leilao.propoe(new Lance(steveJobs, 12000));

		assertEquals(10, leilao.getLances().size());
		assertEquals(11000, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
}
