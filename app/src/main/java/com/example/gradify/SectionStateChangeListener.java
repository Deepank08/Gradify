package com.example.gradify.adapters;

/**
 * Written by team Gradify.
 */

/**
 * interface to listen changes in state of sections
 */

public interface SectionStateChangeListener {
    void onSectionStateChanged(Section section, boolean isOpen);
}
