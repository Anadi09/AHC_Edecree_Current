package com.eDecree.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.eDecree.model.ApplicationWithPetition;
import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.CaseLkoToAldHistory;
import com.eDecree.model.ImpugnedOrder;
import com.eDecree.model.MetaData;
import com.eDecree.model.Petitioner;
import com.eDecree.model.PetitionerCounsel;
import com.eDecree.model.RegularToDefective;
import com.eDecree.model.Respondent;
import com.eDecree.model.RespondentCounsel;
import com.efiling.model.EfilingCaseFileDetail;


@Service
public class CommonReportsService {
	
	

	

	
	

		@PersistenceContext(unitName="persistenceUnitDMS")
		@Qualifier(value = "entityManagerFactoryDMS")
		private EntityManager em;
		
		@PersistenceContext(unitName="persistenceUnitEfiling")
		@Qualifier(value = "entityManagerFactoryEfiling")
		private EntityManager em2;
		
		@PersistenceContext(unitName="persistenceUnitLKODMS")
		@Qualifier(value = "entityManagerFactoryLKODMS")
		private EntityManager lko;
		
		

		@Transactional
		public  Map<String,Object> getFilecavApp() {
			Map<String,Object> m =new HashMap<String,Object>();
			//m.put("hello",12l);
			/*try {
				Object filecount =		(Object) em.createNativeQuery("select count(*) from case_file_details").getResultList();
				System.out.println("on=bjectcccccc"+filecount);
				System.out.println("on=bjectcccccc"+filecount);
				System.out.println("on=bjectcccccc"+filecount);
		//m.put("fileCount",filecount);
				
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}*/
			try {
				Object filecount =		(Object) em2.createNativeQuery("select count(*) from registered_case_details").getSingleResult();
		m.put("fileCount",filecount);
		System.out.println("on=bjectcccccc"+filecount);
		System.out.println("on=bjectcccccc"+filecount);
		System.out.println("on=bjectcccccc"+filecount);
				
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}
			try {
				Object applicationCount =		(Object) em2.createNativeQuery("select count(*) from application").getSingleResult();
				m.put("applicationCount",applicationCount);
						
					}
					catch(Exception e) {
						
						
					}
			try {
				Object caveatCount =		(Object) em2.createNativeQuery("select count(*) from caveat").getSingleResult();
				m.put("caveatCount",caveatCount);
						
					}
					catch(Exception e) {
						
						
					}
			
			return m;
			
		}



		public Object getcaseTypeReport() {
			
			Object caseTypeReport =new Object();
			try {
				
				 caseTypeReport =(Object) em2.createNativeQuery("select count(*) as casesCount,(select ct_label from case_types where ct_id =rcd_ct_id order by ct_label ) from registered_case_details group by rcd_ct_id").getResultList();

			}
			catch(Exception e) {
				
				return null;

			}
			return caseTypeReport;
		}
		
public Object getcaseStageReport() {
			
			Object caseTypeReport =new Object();
			try {
				
				 caseTypeReport =(Object) em2.createNativeQuery("select (select lk_longname from lookup where lk_id =rcd_stage_lid),count(*) from registered_case_details group by rcd_stage_lid").getResultList();

			}
			catch(Exception e) {
				
				return null;

			}
			return caseTypeReport;
		}

public Object getcavStageReport() {
	
	Object caseTypeReport =new Object();
	try {
		
		 caseTypeReport =(Object) em2.createNativeQuery("select (select lk_longname from lookup where lk_id =cav_stage_lid),count(*) from caveat group by cav_stage_lid").getResultList();

	}
	catch(Exception e) {
		
		return null;

	}
	return caseTypeReport;
}

public Object getappStageReport() {
	
	Object caseTypeReport =new Object();
	try {
		
		 caseTypeReport =(Object) em2.createNativeQuery("select (select lk_longname from lookup where lk_id =ap_stage_lid),count(*) from application group by ap_stage_lid").getResultList();

	}
	catch(Exception e) {
		
		return null;

	}
	return caseTypeReport;
}
/*public List<CaseFileTypeReportDto> getcaseTypeReport1() {
			
	List<CaseFileTypeReportDto> caseTypeReport =new ArrayList<CaseFileTypeReportDto>();
			try {
				
				// caseTypeReport =(List<Object>) em2.createNativeQuery("select count(*) as casesCount,(select ct_label from case_types where ct_id =rcd_ct_id order by ct_label ) from registered_case_details group by rcd_ct_id").getResultList();
				 caseTypeReport =(List<CaseFileTypeReportDto>) em2.createNativeQuery("select count(*) as casesCount,(select ct_label from case_types where ct_id =rcd_ct_id order by ct_label ) from registered_case_details group by rcd_ct_id",CaseFileTypeReportDto.class).getResultList();
				// em2.createNativeQuery(sqlString, resultClass)

			}
			catch(Exception e) {
				e.printStackTrace();
				return null;

			}
			return caseTypeReport;
		}*/

public Map<String,Object> getcaseYearlyReport() {
	Map<String,Object> totalData =new HashMap<String,Object>();
			Object caseTypeReport =new Object();
			Object appTypeReport =new Object();
			Object caveatTypeReport =new Object();
			try {
				
				 caseTypeReport =(Object) em2.createNativeQuery("SELECT extract(year from rcd_cr_date) as yyyy, count(*) as yearly_count FROM registered_case_details GROUP BY 1 order by extract(year from rcd_cr_date)").getResultList();
				 totalData.put("cases",caseTypeReport);

			}
			catch(Exception e) {
				
				return null;

			}
			try {
				
				appTypeReport =(Object) em2.createNativeQuery("SELECT extract(year from ap_cr_date) as yyyy, count(*) as yearly_count FROM application GROUP BY 1 order by extract(year from ap_cr_date)").getResultList();
                 totalData.put("applications",appTypeReport);
			}
			catch(Exception e) {
				
				return null;

			}
			try {
				
				caveatTypeReport =(Object) em2.createNativeQuery("SELECT extract(year from cav_cr_date) as yyyy, count(*) as yearly_count FROM caveat GROUP BY 1 order by extract(year from cav_cr_date)").getResultList();
                totalData.put("caveats",caveatTypeReport);
			}
			catch(Exception e) {
				
				return null;

			}
			
			
			
			return totalData;
		}	
		
		

}
