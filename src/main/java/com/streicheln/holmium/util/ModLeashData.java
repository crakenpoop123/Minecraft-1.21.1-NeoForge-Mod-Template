package com.streicheln.holmium.util;


public interface ModLeashData {
    enum leadTypeStates {
        LEAD,
        HOLMIUM_LEAD
    }

    ThreadLocal<ModLeashData.leadTypeStates> DROP_TYPE = new ThreadLocal<>();

    leadTypeStates getLeadType();
    void setLeadType(leadTypeStates state);
}
