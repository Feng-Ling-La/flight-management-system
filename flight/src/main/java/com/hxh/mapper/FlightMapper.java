package com.hxh.mapper;

import com.hxh.bean.Flight;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 对 “航班信息表” 进行操作
 */
@Mapper
public interface FlightMapper {

    /**
     * 查询所有航班，返回一个List，List<Flight>中的Flight表示返回的这个List集合中的每一个对象都是Flight类型的的
     * @return
             */
    @Select("select * from flight")
    public List<Flight> findAll();

    /**
     * 根据f_id 查询航班，返回一个航班对象 Flight，如果查询不到，则返回null
     * @param f_id 要查询的 航班 的id
     * @return
     */
    @Select("select * from flight where f_id=#{f_id}")
    public Flight findAllById(Integer f_id);

    /**
     * 根据航班 名 查询航班，返回所有航班号为f_num的对象，如果查询不到，则返回null
     * @param f_num 要查询的 航班 的 号
     * @return
     */
    @Select("select * from flight where f_num=#{f_num}")
    public List<Flight> findAllByF_num(String f_num);


    //根据出发地点、到达地点、出发时间查找航班
    @Select("select * from flight where f_start=#{f_start} and f_end=#{f_end} and Date(f_dt)=#{f_dt}")
    public List<Flight> findByF_startAndF_endAndF_dt(String f_start,String f_end,String f_dt);

    @Select("select * from flight where f_start=#{f_start} and f_end=#{f_end}")
    public List<Flight> findByF_startAndF_end(String f_start,String f_end);

    @Select("select * from flight where f_start=#{f_start} and Date(f_dt)=#{f_dt}")
    public List<Flight> findByF_startAndF_dt(String f_start,String f_dt);

    @Select("select * from flight where Date(f_dt)=#{f_dt} and f_end=#{f_end}")
    public List<Flight> findByF_endAndF_dt(String f_end,String f_dt);

    @Select("select * from flight where f_start=#{f_start}")
    public List<Flight> findByF_start(String f_start);

    @Select("select * from flight where f_end=#{f_end}")
    public List<Flight> findByF_end(String f_end);

    @Select("select * from flight where Date(f_dt)=#{f_dt}")
    public List<Flight> findByF_dt(String f_dt);

    //改签其他时段航班
    @Select("select * from flight where f_start=#{f_start} and f_end=#{f_end} and f_dt!=#{f_dt}")
    public List<Flight> findByF_startAndF_endAndOtherF_dt(String f_start,String f_end,String f_dt);
    /**
     * 根据 航班id 删除一个航班信息，
     * @param f_id 要删除的航班的id
     */
    @Delete("delete from flight where f_id=#{f_id}")
    public void deleteFlight(Integer f_id);

    /**
     * 在 Flight 表中新增一个航班信息，f_num,f_start,f_end,f_dt,f_at,f_t,f_s,f_j,f_t_n,f_s_n,f_j_n
     * 这些属性对应 管理员 在添加航班信息时 填写的表格。并将其封转为一个 flight 对象传入使用
     * @param flight
     */
    @Insert("insert into flight(f_num,f_start,f_end,f_dt,f_at,f_t,f_s,f_j,f_t_n,f_s_n,f_j_n) " +
            "values(#{f_num},#{f_start},#{f_end},#{f_dt},#{f_at},#{f_t},#{f_s},#{f_j},#{f_t_n},#{f_s_n},#{f_j_n})")
    public void saveFlight(Flight flight);

    /**
     * 更新航班信息，买完票或删除票之后，对票数减一或加一
     * @param flight
     */
    @Update("UPDATE flight SET f_t_n=#{f_t_n},f_s_n=#{f_s_n},f_j_n=#{f_j_n} " +
            "WHERE f_id=#{f_id}")
    public void updateByF_type(Flight flight);

    //更新f_id所指航班
    @Update("update flight set f_num=#{f_num}, f_start=#{f_start}, f_end=#{f_end}, f_dt=#{f_dt}, f_at=#{f_at} , f_t=#{f_t}, f_s=#{f_s}, f_j=#{f_j}, f_t_n=#{f_t_n}, f_s_n=#{f_s_n}, f_j_n=#{f_j_n} where f_id=#{f_id}")
    public int updateFlight(Flight flight);
}
