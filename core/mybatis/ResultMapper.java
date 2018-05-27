package core.mybatis;

import org.apache.ibatis.annotations.Param;
import result.Bean;

import java.util.List;

public interface ResultMapper {

    Bean selectBeanById(int id);

    List<Bean> selectBeanList(@Param("Region")String Region, @Param("Group")String Group, @Param("Host")String Host);

    List<Bean> selectBeanListByLike(@Param("Region")String Region, @Param("Group")String Group, @Param("Host")String Host, @Param("Member")String Member);

    void insertBean(Bean b);

    void updateBean(Bean b);

    void insertBeanList(List<Bean> list);

    void deleteBean(Bean b);

    void cleanList();



}
