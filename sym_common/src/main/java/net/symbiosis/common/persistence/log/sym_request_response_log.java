package net.symbiosis.common.persistence.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.common.persistence.entity.enumeration.sym_channel;
import net.symbiosis.common.persistence.entity.enumeration.sym_event_type;
import net.symbiosis.common.persistence.entity.enumeration.sym_response_code;
import net.symbiosis.persistence.entity.super_class.sym_entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with Intelli_j IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "request_response_log_id"))
public class sym_request_response_log extends sym_entity<sym_request_response_log> {
    @ManyToOne(optional = false)
    @JoinColumn(name = "channel")
    private sym_channel channel;
    @ManyToOne(optional = false)
    @JoinColumn(name = "event_type")
    private sym_event_type event_type;
	@Column
    private Long system_user_id;
	@Column
    private Long auth_user_id;
    @ManyToOne
    @JoinColumn(name = "response_code")
    private sym_response_code response_code;
    @Column(nullable = false)
    private Date incoming_request_time;
    @Column
    private Date outgoing_response_time;
    @Column(nullable = false, length = 2048)
    private String incoming_request;
    @Column(length = 2048)
    private String outgoing_response;

    public sym_request_response_log(sym_channel channel, sym_event_type event_type, Long system_user_id,
                                    Long auth_user_id, String incoming_request) {
        this.channel = channel;
        this.event_type = event_type;
        this.system_user_id = system_user_id;
        this.auth_user_id = auth_user_id;
        this.incoming_request = incoming_request;
        this.incoming_request_time = new Date();
    }

    public sym_request_response_log setChannel_id(sym_channel channel) {
        this.channel = channel;
        return this;
    }

    public sym_request_response_log setEvent_type_id(sym_event_type event_type) {
        this.event_type = event_type;
        return this;
    }

    public sym_request_response_log setAuth_user_id(Long auth_user_id) {
        this.auth_user_id = auth_user_id;
        return this;
    }

    public sym_request_response_log setSystem_user_id(Long system_user_id) {
        this.system_user_id = system_user_id;
        return this;
    }

    public sym_request_response_log setResponse_code(sym_response_code response_code) {
        this.response_code = response_code;
        return this;
    }

    public sym_request_response_log setIncoming_request_time(Date incoming_request_time) {
        this.incoming_request_time = incoming_request_time;
        return this;
    }

    public sym_request_response_log setOutgoing_response_time(Date outgoing_response_time) {
        this.outgoing_response_time = outgoing_response_time;
        return this;
    }

    public sym_request_response_log setIncoming_request(String incoming_request) {
        this.incoming_request = incoming_request;
        return this;
    }

    public sym_request_response_log setOutgoing_response(String outgoing_response) {
        this.outgoing_response = outgoing_response;
        return this;
    }
}
