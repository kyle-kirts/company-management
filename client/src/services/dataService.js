import axios from 'axios'

const SERVER_ADDRESS = 'http://localhost:4567/api/'

export function getQualification(description) {
    return axios.get(SERVER_ADDRESS + 'qualifications/' + description).then((res) => JSON.parse(res.request.response))
}

export function getQualifications() {
    return axios.get(SERVER_ADDRESS + 'qualifications').then((res) => JSON.parse(res.request.response).sort((a, b) => a.description.localeCompare(b.description)))
}

export function createQualification(description) {
    return axios.post(SERVER_ADDRESS + 'qualifications/' + description, { description: description })
}

export function getWorker(name) {
   return axios.get(SERVER_ADDRESS + 'workers/' + name).then((res) => JSON.parse(res.request.response))
}


export function getWorkers() {
   return axios.get(SERVER_ADDRESS + 'workers').then((res) => JSON.parse(res.request.response).sort((a,b) => a.name.localeCompare(b.name)))
}


export function createWorker(name, qualifications, salary) {
   return axios.post(SERVER_ADDRESS + 'workers/' + name, { name: name, qualifications: qualifications, salary: salary })
}

export function getProjects() {
   return axios.get(SERVER_ADDRESS + 'projects').then((res) => JSON.parse(res.request.response).sort((a,b) => a.name.localeCompare(b.name)))
}

export function getProject(name) {
   return axios.get(SERVER_ADDRESS + 'projects/' + name).then((res) => JSON.parse(res.request.response))
}

export function createProject(name, qualifications, size) {
    return axios.post(SERVER_ADDRESS + 'projects/' + name, {name: name, qualifications: qualifications, size: size})
}

export function start(project) {
   return axios.put(SERVER_ADDRESS + '/start', {name: project})
}

export function finish(project) {
   return axios.put(SERVER_ADDRESS + '/finish', {name: project})
}