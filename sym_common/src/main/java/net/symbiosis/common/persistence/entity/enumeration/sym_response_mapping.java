package net.symbiosis.common.persistence.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.persistence.entity.super_class.sym_entity;

import javax.persistence.*;

/***************************************************************************
 *                                                                         *
 * Created:     11 / 05 / 2019                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(indexes = {@Index(name = "index_system_id_response_code_id",  columnList="system_id, response_code_id", unique = true)})
public class sym_response_mapping extends sym_entity<sym_response_mapping> {

    @Column(nullable = false)
    private String system_id;

    @Column(nullable = false)
    private Long response_code_id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "mapped_response_code_id")
    private sym_response_code mapped_response_code;

    @Column(nullable = false)
    private String mapped_response_message;
}
