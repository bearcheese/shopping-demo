package hu.bearmaster.shopping.dal.model;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hu.bearmaster.shopping.model.Manufacturer;

@Entity
@Table(name = "manufacturer")
public class DbManufacturer implements Manufacturer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String headOffice;

    private DbManufacturer() {
    }

    @Override
    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getHeadOffice() {
        return headOffice;
    }
}
