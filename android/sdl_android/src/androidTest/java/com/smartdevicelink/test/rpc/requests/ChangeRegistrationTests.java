package com.smartdevicelink.test.rpc.requests;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.ChangeRegistration;
import com.smartdevicelink.proxy.rpc.enums.Language;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.JsonUtils;
import com.smartdevicelink.test.TestValues;
import com.smartdevicelink.test.json.rpc.JsonFileReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Hashtable;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.fail;

/**
 * This is a unit test class for the SmartDeviceLink library project class : 
 * {@link com.smartdevicelink.rpc.ChangeRegistration}
 */
public class ChangeRegistrationTests extends BaseRpcTests{
    
    @Override
    protected RPCMessage createMessage(){
        ChangeRegistration msg = new ChangeRegistration();

        msg.setLanguage(TestValues.GENERAL_LANGUAGE);
        msg.setHmiDisplayLanguage(TestValues.GENERAL_LANGUAGE);

        return msg;
    }

    @Override
    protected String getMessageType(){
        return RPCMessage.KEY_REQUEST;
    }

    @Override
    protected String getCommandType(){
        return FunctionID.CHANGE_REGISTRATION.toString();
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(ChangeRegistration.KEY_LANGUAGE, TestValues.GENERAL_LANGUAGE);
            result.put(ChangeRegistration.KEY_HMI_DISPLAY_LANGUAGE, TestValues.GENERAL_LANGUAGE);
        }catch(JSONException e){
        	fail(TestValues.JSON_FAIL);
        }

        return result;
    }

    /**
	 * Tests the expected values of the RPC message.
	 */
    @Test
    public void testRpcValues () { 
    	// Test Values
        Language testLanguage    = ( (ChangeRegistration) msg ).getLanguage();
        Language testHmiLanguage = ( (ChangeRegistration) msg ).getHmiDisplayLanguage();
        
        // Valid Tests
        assertEquals(TestValues.MATCH, TestValues.GENERAL_LANGUAGE, testLanguage);
        assertEquals(TestValues.MATCH, TestValues.GENERAL_LANGUAGE, testHmiLanguage);
   
    	// Invalid/Null Tests
        ChangeRegistration msg = new ChangeRegistration();
        assertNotNull(TestValues.NOT_NULL, msg);
        testNullBase(msg);

        assertNull(TestValues.NULL, msg.getLanguage());
        assertNull(TestValues.NULL, msg.getHmiDisplayLanguage());
    }
    
    /**
     * Tests a valid JSON construction of this RPC message.
     */
    @Test
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.readId(getTargetContext(), getCommandType(), getMessageType());
    	assertNotNull(TestValues.NOT_NULL, commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			ChangeRegistration cmd = new ChangeRegistration(hash);
			
			JSONObject body = JsonUtils.readJsonObjectFromJsonObject(commandJson, getMessageType());
			assertNotNull(TestValues.NOT_NULL, body);
			
			// Test everything in the json body.
			assertEquals(TestValues.MATCH, JsonUtils.readStringFromJsonObject(body, RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals(TestValues.MATCH, JsonUtils.readIntegerFromJsonObject(body, RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());

			JSONObject parameters = JsonUtils.readJsonObjectFromJsonObject(body, RPCMessage.KEY_PARAMETERS);
			assertEquals(TestValues.MATCH, JsonUtils.readStringFromJsonObject(parameters, ChangeRegistration.KEY_LANGUAGE), cmd.getLanguage().toString());
			assertEquals(TestValues.MATCH, JsonUtils.readStringFromJsonObject(parameters, ChangeRegistration.KEY_HMI_DISPLAY_LANGUAGE), cmd.getHmiDisplayLanguage().toString());
		} catch (JSONException e) {
			fail(TestValues.JSON_FAIL);
		}    	
    }
}