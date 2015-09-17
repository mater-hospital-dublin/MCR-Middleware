package org.rippleosi.patient.laborders.store;

import org.apache.camel.Body;
import org.apache.camel.Header;
import org.rippleosi.common.exception.ConfigurationException;
import org.rippleosi.patient.laborders.model.LabOrderDetails;

/**
 */
public class NotConfiguredLabOrderStore implements LabOrderStore {

    @Override
    public String getSource() {
        return "not configured";
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void create(@Header("patientId") String patientId, @Body LabOrderDetails labOrder) {
        throw notImplemented();
    }

    @Override
    public void update(@Header("patientId") String patientId, @Body LabOrderDetails labOrder) {
        throw notImplemented();
    }

    private ConfigurationException notImplemented() {
        return new ConfigurationException("Unable to find a configured " + LabOrderStore.class.getSimpleName() + " instance");
    }
}