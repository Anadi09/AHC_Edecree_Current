package com.eDecree.utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constant {
	
	
	public static String appName="dms";

	private static final Map<Integer, String> PARTYTYPE;
    static {
        Map<Integer, String> aMap = new HashMap< Integer,String>();
        aMap.put(1, "SELLER");
        aMap.put(2, "BUYER");
        aMap.put(3, "WITNESS");
        aMap.put(4, "AUTHORIZED");
        PARTYTYPE = Collections.unmodifiableMap(aMap);
    }
    
    private static Map<Object, Object> FORMS;
    static {
        Map<Object, Object> aMap = new HashMap< Object,Object>();      
        aMap.put(1, "SELELR / OWNER");
        aMap.put(2, "BUYER / TENANT");
        aMap.put(3, "WITNESS");
        aMap.put(4, "AUTHORIZED PARTY");
        aMap.put(5, "PROPERTY DETAILS");
        FORMS = Collections.unmodifiableMap(aMap);
    }
    
    private static final Map<String, String> ATTRIBUTETYPE;
    static {
        Map<String, String> aMap = new HashMap< String,String>();
        aMap.put("unit", "Units");
        aMap.put("unit", "Units");
        aMap.put("unit", "Units");
        aMap.put("unit", "Units");
        ATTRIBUTETYPE = Collections.unmodifiableMap(aMap);
    }
    
    public static final Map<String, String> HolidayType;
    static {
        Map<String, String> aMap = new HashMap< String,String>();
        aMap.put("public", "Public Holidays");
        aMap.put("weekly", "Weekly Holidays");
        HolidayType = Collections.unmodifiableMap(aMap);
    }
    
    public static final Map<String, String> HolidayYear;
    static {
        Map<String, String> aMap = new HashMap< String,String>();
        aMap.put("2015", "2015");
        aMap.put("2016", "2016");
        aMap.put("2017", "2017");
        aMap.put("2018", "2018");
        aMap.put("2019", "2019");
        aMap.put("2020", "2020");
        aMap.put("2021", "2021");
        aMap.put("2022", "2022");
        aMap.put("2023", "2023");
        aMap.put("2024", "2024");
        HolidayYear = Collections.unmodifiableMap(aMap);
    }
}
