package sf.codingcompetition2020;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import sf.codingcompetition2020.structures.Agent;
import sf.codingcompetition2020.structures.Claim;
import sf.codingcompetition2020.structures.Customer;

public class CodingCompCsvUtilTest{
	
	private final String agentFilePath = "src/main/resources/DataFiles/agents.csv";
	private final String claimFilePath = "src/main/resources/DataFiles/claims.csv";
	private final String customerFilePath = "src/main/resources/DataFiles/customers.csv";
	private final String vendorFilePath = "src/main/resources/DataFiles/vendors.csv";
	private final String agentList = "agentList";
	private final String claimList = "claimList";
	private final String customerList = "customerList";
	
	
	CodingCompCsvUtil codingCompCsVUtil = new CodingCompCsvUtil();
	
	//#1
	@Test
	public void test1() {
		assertEquals("Giacopo",((Agent)codingCompCsVUtil.readCsvFile(agentFilePath, Agent.class).get(1)).getFirstName());
		assertEquals(424, ((Claim)codingCompCsVUtil.readCsvFile(claimFilePath, Claim.class).get(423)).getClaimId());
		assertEquals("Lorin", ((Customer)codingCompCsVUtil.readCsvFile(customerFilePath, Customer.class).get(499)).getFirstName());
	}
	
	//#2
	@Test
	public void getAgentCountInArea() {
		assertEquals(247,codingCompCsVUtil.getAgentCountInArea(agentFilePath, "area-4"));
		assertEquals(55,codingCompCsVUtil.getAgentCountInArea(agentFilePath, "area-2"));
	}
	
	//#3
	@Test
	public void getAgentsInAreaThatSpeakLanguage() {
		List<Agent> agentList = codingCompCsVUtil.getAgentsInAreaThatSpeakLanguage(agentFilePath, "area-3", "English");
		assertEquals(2, agentList.size());
		assertEquals(49, agentList.get(0).getAgentId());
		assertEquals(424, agentList.get(1).getAgentId());
		
		agentList = codingCompCsVUtil.getAgentsInAreaThatSpeakLanguage(agentFilePath, "area-2", "Spanish");
		assertEquals(1, agentList.size());
		assertEquals(242, agentList.get(0).getAgentId());
	}
	
	//#4
	@Test
	public void countCustomersFromCitythatUseAgent() {
		Map<String, String> csvFilePaths = new HashMap<>();
		
		csvFilePaths.put(agentList, agentFilePath);
		csvFilePaths.put(customerList, customerFilePath);

		assertEquals(4,codingCompCsVUtil.countCustomersFromAreaThatUseAgent(csvFilePaths, "area-3", "Piggy","Ferrai"));
		assertEquals(6,codingCompCsVUtil.countCustomersFromAreaThatUseAgent(csvFilePaths, "area-4", "Rabi","Figg"));
	}
	
	//#5
	@Test
	public void getCustomersRetainedForYearsByPlcyCostAsc() {
		List<Customer> customerList = codingCompCsVUtil.getCustomersRetainedForYearsByPlcyCostAsc(customerFilePath, Short.valueOf("5"));
		
		assertEquals(15,customerList.size());
		assertEquals(215,customerList.get(0).getCustomerId());
		assertEquals(5,customerList.get(2).getYearsOfService());
		assertEquals("$388",customerList.get(3).getTotalMonthlyPremium());
		assertEquals("Janka",customerList.get(4).getFirstName());
		assertEquals("Tesoe",customerList.get(5).getLastName());
		assertEquals("$888",customerList.get(14).getTotalMonthlyPremium());
	}
	
	//#6
	@Test
	public void getLeadsForInsurance() {
		assertEquals(82, codingCompCsVUtil.getLeadsForInsurance(customerFilePath).size());
	}
	
	//#7
	@Test
	public void getVendorsWithGivenRatingThatAreInScope() {
		assertEquals(11, codingCompCsVUtil.getVendorsWithGivenRatingThatAreInScope(vendorFilePath, "area-5", true, 4).size());
		assertEquals(2, codingCompCsVUtil.getVendorsWithGivenRatingThatAreInScope(vendorFilePath, "area-2", true, 2).size());
		assertEquals(12, codingCompCsVUtil.getVendorsWithGivenRatingThatAreInScope(vendorFilePath, "area-3", false, 3).size());
	}
	
	//#8
	@Test
	public void getCustomersRetainedForYearsByPlcyCostAsc2() {		
		assertEquals(15,codingCompCsVUtil.getUndisclosedDrivers(customerFilePath,2,2).size());
		assertEquals(14,codingCompCsVUtil.getUndisclosedDrivers(customerFilePath,3,3).size());
	}
	
	//#9
	@Test
	public void getAgentIdGivenRank() {		
		assertEquals(3,codingCompCsVUtil.getAgentIdGivenRank(customerFilePath, 1));
		assertEquals(12,codingCompCsVUtil.getAgentIdGivenRank(customerFilePath, 4));
		assertEquals(14,codingCompCsVUtil.getAgentIdGivenRank(customerFilePath, 20));
	}
	
	//#10
	@Test
	public void getCountCustomersWithClaims() {
		Map<String, String> csvFilePaths = new HashMap<>();
		
		csvFilePaths.put(customerList, customerFilePath);
		csvFilePaths.put(claimList, claimFilePath);
			
		assertEquals(81,codingCompCsVUtil.getCustomersWithClaims(csvFilePaths, Short.valueOf("1")).size());
		assertEquals(312,codingCompCsVUtil.getCustomersWithClaims(csvFilePaths, Short.valueOf("6")).size());
		}

}

