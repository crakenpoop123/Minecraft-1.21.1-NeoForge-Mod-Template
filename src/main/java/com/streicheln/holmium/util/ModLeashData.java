package com.streicheln.holmium.util;


public interface ModLeashData {
    enum leadTypeStates {
        LEAD,
        HOLMIUM_LEAD
    }

    leadTypeStates getLeadType();
    void setLeadType(leadTypeStates state);
}
