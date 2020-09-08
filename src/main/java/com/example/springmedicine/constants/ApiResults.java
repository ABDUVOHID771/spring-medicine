package com.example.springmedicine.constants;

import com.example.springmedicine.dao.domain.base.Results;

import java.util.Date;

public class ApiResults {
    public static final Results OK = new Results(200, "NO_ERROR", new Date());
    public static final Results ERR_001 = new Results(404, "NOT_FOUND", new Date());
    public static final Results ERR_002 = new Results(500, "SERVER_ERROR", new Date());
    public static final Results ERR_003 = new Results(400, "BAD_REQUEST", new Date());
    public static final Results ERR_004 = new Results(409, "CONFLICT", new Date());


}
