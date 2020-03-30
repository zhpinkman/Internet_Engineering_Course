import * as React from "react";


export default class Orderitem extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            user : {},
            index: props.index
        }
    }


    render() {
        return (
                <div className="order-item">
                    <div className="row">
                        <div className="col-2">
                            <div className="order-index">
                                    <span>
                                        {this.state.index}
                                    </span>
                            </div>
                        </div>
                        <div className="col-6">
                            <div className="order-restaurant">
                                <span>رستوران خامس</span>
                            </div>
                        </div>
                        <div className="col-4">
                            <div className="order-status">
                                <div className="status-box delivery">
                                        <span>
                                            پیک در مسیر
                                        </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        );
    }

}