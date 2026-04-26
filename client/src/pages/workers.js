import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { getWorkers } from '../services/dataService'
import { createWorker } from '../services/dataService'
import LocationID from '../utils/location'
import { darkGrayContainerStyle, grayContainerStyle, pageStyle } from '../utils/styles'

const Worker = (worker, active) => {
   return (
       <div>
           <div>{worker.name}</div>
           {active === true ? WorkerBody(worker) : null}
       </div>
   )
}

const WorkerBody = (worker) => {
    return (
        <div style={grayContainerStyle}>
            <div>
                Name: {worker.name}
            </div>

            <div>
                Salary: {worker.salary}
            </div>

            <div>
                Current Workload: {worker.workload}
            </div>

            <div>
                Qualifications: <ClickList list={worker.qualifications} styles={darkGrayContainerStyle} path="/qualifications" />
            </div>

            <div>
                Projects: <ClickList list={worker.projects} styles={darkGrayContainerStyle} path="/projects" />
            </div>
            
        </div>
    )
}

const Workers = () => {
    const [workers, setWorkers] = useState([])
    useEffect(() => { getWorkers().then(setWorkers) }, [])
    const active = LocationID('workers', workers, 'name')
    return (
        <div style={pageStyle}>
            <h1>
                Company Workers
            </h1>
            <ClickList active={active} list={workers} item={Worker} path='/workers' id='name' />
        </div>
    )
}

export default Workers