package com.demo.transformer;

import com.demo.database.entity.Employee;
import com.demo.dto.EmployeeDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeToEmployeeDtoTransformer {
    public EmployeeDto transform(Employee employee) {
        return new EmployeeDto(
                StringUtils.trimToNull(employee.id()),
                StringUtils.trimToNull(employee.firstName()),
                StringUtils.trimToNull(employee.lastName()),
                employee.numberOfDependents(),
                employee.height(),
                employee.weight(),
                employee.hiredDate(),
                employee.startTime(),
                employee.isRegular());
    }
}
