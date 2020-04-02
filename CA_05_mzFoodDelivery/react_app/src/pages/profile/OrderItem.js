import * as React from "react";
import PropTypes from "prop-types";
import Translator from "../../utils/Translator";
import OrderDetail from "./OrderDetail";
import Modal from "react-bootstrap/Modal";

export default class Orderitem extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            show: false
        };

        this.getStatusStyle = this.getStatusStyle.bind(this);
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
    }

    handleClose() {
        this.setState({ show: false });

    }

    handleShow() {
        if (this.props.order.status) {
            this.setState({show: true});
        }
    }


    getStatusStyle(status) {
        let style = "status-box ";
        switch (status) {
            case "SEARCHING":
                return style + "searching";
            case "DELIVERING":
                return style + "delivering";
            case "DELIVERED":
                return style + "delivered clickable";

            default:
                return style
        }

    }

    render() {
        return (
            <>
            <div className="order-item">
                <div className="row">
                    <div className="col-2">
                        <div className="order-index">
                                    <span>
                                        {this.props.index}
                                    </span>
                        </div>
                    </div>
                    <div className="col-6">
                        <div className="order-restaurant">
                                <span>
                                    {this.props.order.cart.restaurant.name}
                                </span>
                        </div>
                    </div>
                    <div className="col-4">
                        <div className="order-status">
                            <div className={this.getStatusStyle(this.props.order.status)} onClick={this.handleShow}>
                                <span>
                                    {Translator.toFa(this.props.order.status)}
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <Modal size="lg" show={this.state.show} onHide={this.handleClose}>
            <OrderDetail cart={this.props.order.cart}/>
            </Modal>
        </>
        );
    }

}

Orderitem.propTypes = {
    index: PropTypes.number,
    order: PropTypes.shape({
        cart: PropTypes.shape({
            restaurant: PropTypes.shape({
                name: PropTypes.string
            })
        }),
        status: PropTypes.string
    })
};