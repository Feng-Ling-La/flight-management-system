package com.hxh.service.Impl;

import com.hxh.bean.Flight;
import com.hxh.mapper.FlightMapper;
import com.hxh.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightMapper flightMapper;

    @Override
    public List<Flight> findAll() {
        return flightMapper.findAll();
    }

    @Override
    public Flight findAllById(Integer f_id) {
        return flightMapper.findAllById(f_id);
    }

    @Override
    public List<Flight> findAllByF_num(String f_num) {
        return flightMapper.findAllByF_num(f_num);
    }

    @Override
    public List<Flight> findByF_startAndF_endAndF_dt(String f_start, String f_end, String f_dt) {
        return flightMapper.findByF_startAndF_endAndF_dt(f_start,f_end,f_dt);
    }

    @Override
    public List<Flight> findByF_startAndF_end(String f_start, String f_end) {
        return flightMapper.findByF_startAndF_end(f_start,f_end);
    }

    @Override
    public List<Flight> findByF_startAndF_dt(String f_start, String f_dt) {
        return flightMapper.findByF_startAndF_dt(f_start,f_dt);
    }

    @Override
    public List<Flight> findByF_endAndF_dt(String f_end, String f_dt) {
        return flightMapper.findByF_endAndF_dt(f_end,f_dt);
    }

    @Override
    public List<Flight> findByF_start(String f_start) {
        return flightMapper.findByF_start(f_start);
    }

    @Override
    public List<Flight> findByF_end(String f_end) {
        return flightMapper.findByF_end(f_end);
    }

    @Override
    public List<Flight> findFlightsByF_dt(String f_dt) {
        return flightMapper.findByF_dt(f_dt);
    }

    @Override
    public List<Flight> findByF_startAndF_endAndOtherF_dt(String f_start, String f_end, String f_dt) {
        return flightMapper.findByF_startAndF_endAndOtherF_dt(f_start,f_end,f_dt);
    }


    @Override
    public void deleteFlight(Integer f_id) {
        flightMapper.deleteFlight(f_id);
    }

    @Override
    public void saveFlight(Flight flight) {
        flightMapper.saveFlight(flight);
    }

    @Override
    public void updateByF_type(Flight flight) {
        flightMapper.updateByF_type(flight);
    }

    @Override
    public int updateFlight(Flight flight) {
        return flightMapper.updateFlight(flight);
    }
}
