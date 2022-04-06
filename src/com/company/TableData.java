package com.company;

import java.io.Serializable;
import java.util.List;

public interface TableData  {
    String [] getLabels();
    List<List<Object>> getData();
}
