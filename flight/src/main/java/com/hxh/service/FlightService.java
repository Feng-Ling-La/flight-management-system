package com.hxh.service;

import com.hxh.bean.Flight;

import java.util.List;

public interface FlightService {
    public List<Flight> findAll();

    public Flight findAllById(Integer f_id);

    public List<Flight> findAllByF_num(String f_num);

    public List<Flight> findByF_startAndF_endAndF_dt(String f_start,String f_end,String f_dt);

    public List<Flight> findByF_startAndF_end(String f_start,String f_end);

    public List<Flight> findByF_startAndF_dt(String f_start,String f_dt);

    public List<Flight> findByF_endAndF_dt(String f_end,String f_dt);

    public List<Flight> findByF_start(String f_start);

    public List<Flight> findByF_end(String f_end);

    public List<Flight> findFlightsByF_dt(String f_dt);

    public List<Flight> findByF_startAndF_endAndOtherF_dt(String f_start,String f_end,String f_dt);

    public void deleteFlight(Integer f_id);

    public void saveFlight(Flight flight);

    public void updateByF_type(Flight flight);

    public int updateFlight(Flight flight);
}
