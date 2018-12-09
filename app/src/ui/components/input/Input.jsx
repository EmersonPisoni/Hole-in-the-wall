import React, { Component, Fragment } from 'react'
import { Certo, Errado } from './../../../assets'

import './Input.css'

export class Input extends Component {

    constructor() {
        super()

        this.state = {
            icone: null
        }
    }

    checagemOnBlur = (event) => {
        if (event.target.value) {
            this.setState({
                icone: Certo
            })
        }

        else {
            this.setState({
                icone: Errado
            })
        }
    }

    render() {
        return (
            <Fragment>
                <div className="input-grupo">
                    <input className="input-campo" text="text" placeholder={this.props.placeholder}
                        onChange={this.props.onChange} onBlur={this.checagemOnBlur}
                        type={this.props.inputType} name={this.props.name} value={this.props.value} autoComplete="off" />
                    {this.state.icone !== null && (
                        <img className="input-checagem" src={this.state.icone} alt="checagem" />
                    )}
                </div>
                {this.props.mensagemErro && <p className="input-erro">{this.props.mensagemErro}</p>}
            </Fragment>
        )
    }

}
