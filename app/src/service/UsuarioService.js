import axios from 'axios'
import BaseService from './BaseService'

export class UsuarioService extends BaseService {

    constructor() {
        super()

        this.TOKEN_KEY = 'TOKEN'
    }

    login(emailOuApelido, senha) {
        const dados = {
            emailOuApelido,
            senha
        }

        return axios.post(`${this.baseUrl}login`, dados)
    }

    cadastrar(nome, apelido, email, senha) {
        const usuario = {
            nome,
            apelido,
            email,
            senha
        }

        return axios.post(`${this.baseUrl}usuarios`, usuario)
    }

    salvarToken(token) {
        localStorage.setItem(this.TOKEN_KEY, token)
    }

    getToken() {
        return localStorage.getItem(this.TOKEN_KEY)
    }

    removerToken() {
        return localStorage.removeItem(this.TOKEN_KEY)
    }

}

export default UsuarioService
