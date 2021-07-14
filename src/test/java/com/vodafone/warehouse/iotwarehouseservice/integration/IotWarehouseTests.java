package com.vodafone.warehouse.iotwarehouseservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.warehouse.iotwarehouseservice.constant.DeviceStatus;
import com.vodafone.warehouse.iotwarehouseservice.constant.SimStatus;
import com.vodafone.warehouse.iotwarehouseservice.model.IotDeviceRequest;
import com.vodafone.warehouse.iotwarehouseservice.model.SimRequest;
import com.vodafone.warehouse.iotwarehouseservice.repository.dao.DeviceDao;
import com.vodafone.warehouse.iotwarehouseservice.repository.model.DeviceDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class IotWarehouseTests {

    ObjectMapper om = new ObjectMapper();

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private MockMvc mockMvc;

    Map<String, IotDeviceRequest> testData;

    @Before
    public void setup() {
        deviceDao.deleteAll();
        testData = getTestData();
    }

    @Test
    public void testAddDevice_Ready_Active() throws Exception {
        //test new creation
        IotDeviceRequest expectedRecord = testData.get("device_ready_active");
        mockMvc.perform(post("/manage/addDevice")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:password" .getBytes()))
                .contentType("application/json")
                .content(om.writeValueAsString(expectedRecord)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddDevice_Ready_Waiting_Activation() throws Exception {
        //test new creation
        IotDeviceRequest expectedRecord = testData.get("device_ready_waiting_activation");
        mockMvc.perform(post("/manage/addDevice")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:password" .getBytes()))
                .contentType("application/json")
                .content(om.writeValueAsString(expectedRecord)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddDevice_Waiting_Active() throws Exception {
        //test new creation
        IotDeviceRequest expectedRecord = testData.get("device_waiting_active");
        mockMvc.perform(post("/manage/addDevice")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:password" .getBytes()))
                .contentType("application/json")
                .content(om.writeValueAsString(expectedRecord)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddDevice_Waiting_Waiting_Activation() throws Exception {
        //test new creation
        IotDeviceRequest expectedRecord = testData.get("device_waiting_waiting_activation");
        mockMvc.perform(post("/manage/addDevice")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:password" .getBytes()))
                .contentType("application/json")
                .content(om.writeValueAsString(expectedRecord)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllDevices_Success() throws Exception {
        mockMvc.perform(get("/getAllDevices"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateDevices_Success() throws Exception {
        IotDeviceRequest expectedRecord = testData.get("device_ready_active");
        ResultActions addResultAction = mockMvc.perform(post("/manage/addDevice")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:password" .getBytes()))
                .contentType("application/json")
                .content(om.writeValueAsString(expectedRecord)))
                .andDo(print())
                .andExpect(status().isCreated());
        DeviceDto deviceDto = om.readValue(addResultAction.andReturn().getResponse().getContentAsString(),
                DeviceDto.class);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("deviceId", deviceDto.getDeviceId());
        mockMvc.perform(put("/manage/updateDevice/{deviceId}", deviceDto.getDeviceId())
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:password" .getBytes()))
                .contentType("application/json")
                .content(om.writeValueAsString(expectedRecord)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddDevice_400_Invalid_Request() throws Exception {
        //test new creation
        IotDeviceRequest expectedRecord = testData.get("device_ready_active");
        expectedRecord.setDeviceStatus(null);
        mockMvc.perform(post("/manage/addDevice")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:password" .getBytes()))
                .contentType("application/json")
                .content(om.writeValueAsString(expectedRecord)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddDevice_401_UnAuthorized() throws Exception {
        //test new creation
        IotDeviceRequest expectedRecord = testData.get("device_ready_active");
        expectedRecord.setDeviceStatus(null);
        mockMvc.perform(post("/manage/addDevice")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:abcd" .getBytes()))
                .contentType("application/json")
                .content(om.writeValueAsString(expectedRecord)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    private Map<String, IotDeviceRequest> getTestData() {
        Map<String, IotDeviceRequest> data = new HashMap<>();

        IotDeviceRequest device_ready_active = new IotDeviceRequest(DeviceStatus.READY,
                25,
                new SimRequest("ABCD",
                        "England",
                        SimStatus.ACTIVE));
        data.put("device_ready_active", device_ready_active);
        IotDeviceRequest device_ready_waiting_activation = new IotDeviceRequest(DeviceStatus.READY,
                25,
                new SimRequest("ABCD",
                        "England",
                        SimStatus.WAITING_FOR_ACTIVATION));
        data.put("device_ready_waiting_activation", device_ready_waiting_activation);
        IotDeviceRequest device_waiting_active = new IotDeviceRequest(DeviceStatus.READY,
                25,
                new SimRequest("ABCD",
                        "England",
                        SimStatus.ACTIVE));
        data.put("device_waiting_active", device_waiting_active);
        IotDeviceRequest device_waiting_waiting_activation = new IotDeviceRequest(DeviceStatus.READY,
                25,
                new SimRequest("ABCD",
                        "England",
                        SimStatus.WAITING_FOR_ACTIVATION));
        data.put("device_waiting_waiting_activation", device_waiting_waiting_activation);
        return data;
    }

}
