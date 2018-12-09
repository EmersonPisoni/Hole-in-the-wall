import React, { Component, Fragment } from 'react';
import axios from 'axios';
import { Route } from 'react-router-dom'
import { Cadastro, Login, Jogo} from './ui/screens'
import { Loader } from './ui/components'

import background from './assets/partida.png'

import './App.css';

class App extends Component {

    constructor(props) {
        super(props)

        this.state = {
            carregando: false
        }

        this.axiosConfig()
    }

    render() {
        return (
            <Fragment>
                <link rel="prefetch prerender" href={background} />

                <Route render={(props) => <Jogo {...props} toggleLoading={this.toggleLoading} />} path="/" exact />
                <Route component={Cadastro} path="/cadastro" />
                <Route component={Login} path="/login" />
                {this.renderLoader()}
            </Fragment>
        )
    }

    renderLoader = () => {
        if (this.state.carregando) {
            return <Loader />;
        }

        else {
            return null;
        }
    }

    axiosConfig = () => {
        axios.interceptors.request.use((config) => {
            if (config.loader !== false) {
                this.toggleLoading()
            }

            return config;
        })

        axios.interceptors.response.use((response) => {
            if (response.config.loader !== false) {
                this.toggleLoading()
            }

            return response;
        }
         ,(error) => {
            if (error.config.loader !== false) {
                this.toggleLoading()
            }

            return Promise.reject(error);
        })
    }

    toggleLoading = () => {
        this.setState({
            carregando: !this.state.carregando
        })
    }

}

export default App;
